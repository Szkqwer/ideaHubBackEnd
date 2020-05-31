package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.ResultInfo;
import whu.service.HubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hubPage/shareHub")
public class ShareHubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hubID = request.getParameter("hubId");
        String targetID = request.getParameter("targetId");
        String sign = request.getParameter("sign");
        HubService service = new HubService();
        ResultInfo info = new ResultInfo();
        boolean flag = service.shareHub(hubID, targetID, Integer.parseInt(sign));
        if (flag) {
            info.setCode(1);
            info.setMsg("分享成功");
        } else {
            info.setCode(0);
            info.setMsg("分享失败");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
        System.out.println(json);
    }
}
