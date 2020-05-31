package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.ResultInfo;
import whu.beans.User;
import whu.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 点赞
 */
@WebServlet("/homePage/likeArticle")
public class ThumbUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u=new User();
        u.setUserID(1);
        session.setAttribute("user",u);
        User user =(User) session.getAttribute("user");
        int id=user.getUserID();
        String articleID = request.getParameter("articleId");
        ArticleService service=new ArticleService();
        ResultInfo info=new ResultInfo();
        boolean flag=service.thumbUp(id,articleID);
        if (flag){
            info.setCode(1);
            info.setMsg("点赞成功");
        }else {
            info.setCode(0);
            info.setMsg("点赞失败，已点赞或文章不存在");
        }

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
        System.out.println(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
