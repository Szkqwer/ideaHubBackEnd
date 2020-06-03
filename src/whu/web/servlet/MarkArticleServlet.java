package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.ResultInfo;
import whu.beans.User;
import whu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/homePage/markArticle")
public class MarkArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleID = request.getParameter("articleId");

//        String userID = request.getParameter("userId");
        HttpSession session = request.getSession();
        User tempUser = (User) session.getAttribute("user");
        String userID=String.valueOf(tempUser.getUserID());

        UserService service=new UserService();
        ResultInfo info=new ResultInfo();
        boolean flag=service.markArticle(articleID,userID);
        if (flag){
            info.setCode(1);
            info.setMsg("收藏成功");
        }else {
            info.setCode(0);
            info.setMsg("收藏失败");
        }

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
        System.out.println(json);
    }
}
