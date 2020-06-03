package whu.web.servlet.newFunction;

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

@WebServlet("/signPage/signIn")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        User user=new User();
        ResultInfo info=new ResultInfo();
        UserService service=new UserService();
        user=service.findUserById(userId);
        if (user==null){
            info.setCode(-1);
            info.setMsg("账号不存在");
        }else {
            user=service.loginUser(userId,password);
            if (user==null){
                info.setCode(0);
                info.setMsg("密码不正确");
            }else {
                info.setCode(1);
                info.setMsg("登录成功");
                session.setAttribute("user",user);
            }
        }



        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
    }
}
