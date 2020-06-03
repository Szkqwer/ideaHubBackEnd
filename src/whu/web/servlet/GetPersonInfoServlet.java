package whu.web.servlet;

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

@WebServlet("/homePage/getPersonalInfo")
public class GetPersonInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String userID = request.getParameter("userId");
        HttpSession session = request.getSession();
        User tempUser = (User) session.getAttribute("user");
        String userID=String.valueOf(tempUser.getUserID());


        UserService service=new UserService();
        User user=service.getPersonalInfo(userID);
        ResultInfo info=new ResultInfo();
        if (true){
            info.setCode(1);
            info.setMsg("获取个人信息成功");
            info.setData(user);
        }else {
            info.setCode(0);
            info.setMsg("获取个人信息失败");
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
