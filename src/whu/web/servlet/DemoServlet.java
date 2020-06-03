package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import whu.beans.User;
import whu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userID= request.getParameter("userID");
        HttpSession session = request.getSession();
        User tempUser = (User) session.getAttribute("user");
        String userID=String.valueOf(tempUser.getUserID());

        UserService service=new UserService();
        User user = service.findUserById(userID);

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
        System.out.println(json);


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
