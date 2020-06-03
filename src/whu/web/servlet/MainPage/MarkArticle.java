package whu.web.servlet.MainPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.*;
import whu.service.ArticleService;
import whu.service.UserService;
import whu.service.UserMarkArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 收藏
 */
@WebServlet("/mainPage/markArticle")
public class MarkArticle extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将前端传递的参数剥离
        // request.getParameter此方法要求前端提交参数时的编码格式为application/x-www-form-urlencoded
        // 如果前端提交参数时的编码格式为application/json或multipart/form-data则不能被识别
//        String userID = req.getParameter("userId");
        HttpSession session = req.getSession();
        User tempUser = (User) session.getAttribute("user");
        String userID=String.valueOf(tempUser.getUserID());

        String articleID = req.getParameter("articleId");
        // 查询用户
        UserService userService = new UserService();
        User user = userService.findUserById(userID);
        // 查询文章
        ArticleService articleService = new ArticleService();
        Article article =articleService.findArticleById(articleID);
        // 返回信息的初始化
        ResultInfo info=new ResultInfo();
        // 用户存在且文章存在
        if (user != null && article != null){
            // 生成评论实例，添加到数据库
            UserMarkArticle userMarkArticle = new UserMarkArticle(Integer.parseInt(userID), Integer.parseInt(articleID));
            UserMarkArticleService userMarkArticleService = new UserMarkArticleService();
            if(userMarkArticleService.addMark(userMarkArticle)){
                info.setCode(1);
                info.setMsg("收藏成功");
                info.setCount(0);
                info.setData(null);
            }else {
                info.setCode(0);
                info.setMsg("收藏失败，数据库崩溃");
            }
        }else {
            info.setCode(0);
            info.setMsg("收藏失败，不存在该用户或内容为空或原评论已被删除");
        }

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json); //字符流写回
        System.out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
