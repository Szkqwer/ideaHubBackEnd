package whu.web.servlet.MainPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.*;
import whu.service.ArticleService;
import whu.service.UserMarkArticleService;
import whu.service.UserService;
import whu.service.UserLikeArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  点赞
 */
@WebServlet("/mainPage/likeArticle")
public class LikeArticle extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将前端传递的参数剥离
        // request.getParameter此方法要求前端提交参数时的编码格式为application/x-www-form-urlencoded
        // 如果前端提交参数时的编码格式为application/json或multipart/form-data则不能被识别
        String articleID = req.getParameter("articleId");

//        String likeUserID = req.getParameter("likeUserId");
        HttpSession session = req.getSession();
        User tempUser = (User) session.getAttribute("user");
        String likeUserID=String.valueOf(tempUser.getUserID());



        // 查询用户
        UserService userService = new UserService();
        User likeUser = userService.findUserById(likeUserID);
        // 查询文章
        ArticleService articleService = new ArticleService();
        Article article =articleService.findArticleById(articleID);
        // 返回信息的初始化
        ResultInfo info=new ResultInfo();
        // 用户存在且文章存在
        if (likeUser != null && article != null){
            // 生成评论实例，添加到数据库
            UserLikeArticle userLikeArticle = new UserLikeArticle(Integer.parseInt(likeUserID), Integer.parseInt(articleID));
            UserLikeArticleService userLikeArticleService = new UserLikeArticleService();
            if(userLikeArticleService.addLike(userLikeArticle)){
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
