package whu.web.servlet.MainPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Article;
import whu.beans.ResultInfo;
import whu.beans.User;
import whu.beans.UserShareArticle;
import whu.service.ArticleService;
import whu.service.UserService;
import whu.service.UserShareArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 分享文章
 */
@WebServlet("/mainPage/shareArticle")
public class ShareArticle extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

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
        // 数据库表不同，可能用hub代替circle
        // String targetHubID = req.getParameter("targetHubId");
        String targetCircleID = req.getParameter("targetCircleId");
        String targetUserID = req.getParameter("targetUserId");
        // 查询用户
        UserService userService = new UserService();
        User user = userService.findUserById(userID);
        User targetUser = userService.findUserById(targetUserID);
        // 查询文章
        ArticleService articleService = new ArticleService();
        Article article =articleService.findArticleById(articleID);
        // 查询Hub, 每个人数据库表不同，可能用circle也可能用hub，写法格式在下
        // CircleService crticleService = new CircleService();
        // Circle circle =crticleService.findCircleById(targetCircleID);
        // 或是hub版本
        // HubService hubService = new HubService();
        // Hub hub =hubService.findHubById(targetHubID);
        // 返回信息的初始化
        ResultInfo info=new ResultInfo();
        // 用户存在且文章存在且目标用户存在且目标圈存在
        // 下方逻辑中还应保证targetHub存在，即 && targetHub != null
        if (user != null && article != null && targetUser != null ){
            // 生成评论实例，添加到数据库
            UserShareArticle userShareArticle = new UserShareArticle(Integer.parseInt(userID), Integer.parseInt(articleID),
                    Integer.parseInt(targetCircleID), Integer.parseInt(targetUserID));
            UserShareArticleService userShareArticleService = new UserShareArticleService();
            if(userShareArticleService.addShare(userShareArticle)){
                info.setCode(1);
                info.setMsg("分享成功");
                info.setCount(0);
                info.setData(null);
            }else {
                info.setCode(0);
                info.setMsg("分享失败，数据库崩溃");
            }
        }else {
            info.setCode(0);
            info.setMsg("分享失败，不存在该用户或内容为空或原评论已被删除");
        }

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json); //字符流写回
        System.out.println(json);
    }
}
