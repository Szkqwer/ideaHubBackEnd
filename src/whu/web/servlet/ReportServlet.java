package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Hub;
import whu.beans.ResultInfo;
import whu.beans.User;
import whu.service.HubService;
import whu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/hubPage/report")
public class ReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String circleID=request.getParameter("circleId");
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        String userID=String.valueOf(user.getUserID());
        String report =request.getParameter("report");

        HubService service=new HubService();
        boolean flag=service.report(circleID,userID,report);
        ResultInfo info=new ResultInfo();
        if (flag){
            info.setCode(1);
            info.setMsg("举报成功,请等待反馈消息");
        }else {
            info.setCode(0);
            info.setMsg("举报失败");
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
