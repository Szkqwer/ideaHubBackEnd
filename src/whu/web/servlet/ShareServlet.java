package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.ResultInfo;
import whu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/homePage/shareArticle")
public class ShareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleID = request.getParameter("articleId");
        String userID = request.getParameter("userId");
        String targetUserID = request.getParameter("targetUserId");
        String targetCircleID = request.getParameter("targetCircleId");
        UserService service=new UserService();
        boolean flag;
        if (targetUserID==null||targetUserID.length()==0){
            flag=service.shareArticle(articleID,userID,targetCircleID,0);
        }else {
            flag=service.shareArticle(articleID,userID,targetUserID,1);
        }
        ResultInfo info=new ResultInfo();
        if (flag){
            info.setCode(1);
            info.setMsg("分享成功");
        }else {
            info.setCode(0);
            info.setMsg("分享失败");
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
