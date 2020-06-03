package whu.web.servlet.MainPage;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.jetbrains.annotations.NotNull;
import whu.beans.*;

import whu.service.ArticleService;
import whu.service.UserReportArticleService;
import whu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 举报文章
 */
@WebServlet("/main/reportArticle")
public class ReportArticle extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String articleID= req.getParameter("articleId"); //文章ID
//        String reportUserID= req.getParameter("reportUserID"); // 用户ID
        HttpSession session = req.getSession();
        User tempUser = (User) session.getAttribute("user");
        String reportUserID=String.valueOf(tempUser.getUserID());



        String report= req.getParameter("report"); // 举报理由
        // 查询文章
        ArticleService articleService = new ArticleService();
        Article article =articleService.findArticleById(articleID);
        // 查询用户
        UserService userService = new UserService();
        User reportUser = userService.findUserById(reportUserID);
        // 返回信息的初始化
        ResultInfo info=new ResultInfo();
        // 用户存在且文章存在且内容不为空
        if (article != null && reportUser != null && report != null && report.length()>0){
            // 生成评论实例，添加到数据库
            UserReportArticle userReportArticle = new UserReportArticle(Integer.parseInt(reportUserID), Integer.parseInt(articleID), report);
            UserReportArticleService UserReportArticleService = new UserReportArticleService();
            if(UserReportArticleService.addReport(userReportArticle)){
                info.setCode(1);
                info.setMsg("举报成功");
                info.setCount(0);
                info.setData(null);
            }else {
                info.setCode(0);
                info.setMsg("举报失败");
            }
        }else {
            info.setCode(0);
            info.setMsg("举报失败，不存在该用户或内容为空或原评论已被删除");
        }
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json); //字符流写回
        System.out.println(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
