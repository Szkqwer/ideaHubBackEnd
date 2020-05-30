package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Article;
import whu.beans.Hub;
import whu.beans.ResultInfo;
import whu.service.ArticleService;
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
        String hubId = request.getParameter("hubId");
        HubService service=new HubService();
        ResultInfo info=new ResultInfo();
        Hub hub =service.getHubDetail(hubId);
        System.out.println(hub.getSetupTime());
        if (hub!=null){
            //用户存在
            info.setCode(1);
            info.setMsg("获取圈子详情成功");
            info.setCount(1);
            info.setData(hub);
        }else {
            info.setCode(0);
            info.setMsg("获取圈子详情失败");
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
