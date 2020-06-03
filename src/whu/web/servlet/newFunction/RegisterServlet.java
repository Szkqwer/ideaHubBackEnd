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
import java.util.Random;

@WebServlet("/signPage/signUp")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");

        ResultInfo info=new ResultInfo();
        UserService service=new UserService();
        Random random=new Random();
        int userID = random.nextInt(10000000);
        if (userID<1000000){
            userID+=1000000;
        }
        User u=new User();
        u=service.findUserById(String.valueOf(userID));
        while (u!=null){
            userID = random.nextInt(100000000);
            if (userID<10000000){
                userID+=10000000;
            }
            u=service.findUserById(String.valueOf(userID));
        }

        boolean flag=service.registerUser(userID,nickname,password);
        if (flag){
            info.setCode(1);
            info.setMsg("注册成功");
            info.setData(userID);
        }else {
            info.setCode(0);
            info.setMsg("账号已被注册");
        }




        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
    }
}
