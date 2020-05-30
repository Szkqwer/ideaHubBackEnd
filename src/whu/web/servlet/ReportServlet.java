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
import java.io.IOException;

@WebServlet("/hubPage/report")
public class ReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String hubId=request.getParameter("hubId");
        String userId = request.getParameter("userId");
        String report =request.getParameter("report");

        HubService service=new HubService();
        boolean hub=service.report(hubId,userId,report);
        ResultInfo info=new ResultInfo();
        if (true){
            info.setCode(1);
            info.setMsg("举报成功");
            info.setData(hub);
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
