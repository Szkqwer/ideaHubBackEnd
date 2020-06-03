package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Article;
import whu.beans.Circle;
import whu.beans.Hub;
import whu.beans.ResultInfo;
import whu.service.ArticleService;
import whu.service.CircleService;
import whu.service.HubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hubPage/getHubDetail")
public class GetHubDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String circleID = request.getParameter("circleId");
        CircleService service=new CircleService();
        ResultInfo info=new ResultInfo();
        Circle circle=new Circle();
        circle=service.findCircleById(circleID);
        if (circle!=null){
            info.setCode(1);
            info.setMsg("获取圈子详情成功");
            info.setCount(1);
            info.setData(circle);
        }else {
            info.setCode(0);
            info.setMsg("获取圈子详情失败");
        }

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
