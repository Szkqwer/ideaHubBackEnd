package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
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
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/hubPage/joinRequest")
public class JoinRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleID = request.getParameter("hubId");
        String userID = request.getParameter("userId");
        HubService service = new HubService();
        ResultInfo info = new ResultInfo();
        boolean flag = service.joinRequest(articleID, userID);
        if (flag) {
            info.setCode(1);
            info.setMsg("申请已发送");
        } else {
            info.setCode(0);
            info.setMsg("发送失败");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
        System.out.println(json);
    }
}
