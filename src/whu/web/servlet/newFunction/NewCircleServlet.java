package whu.web.servlet.newFunction;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Circle;
import whu.beans.ResultInfo;
import whu.beans.User;
import whu.service.CircleService;
import whu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@WebServlet("/circlePage/createCircle")
public class NewCircleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String digest = request.getParameter("digest");
        String imagePath = request.getParameter("imagePath");
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        Circle circle=new Circle();
        circle.setDigest(digest);
        circle.setCircleLeaderId(user.getUserID());
        circle.setCircleLeaderNickName(user.getNickName());
        circle.setImagePath(imagePath);
        circle.setName(name);
        circle.setSetupTime(new Date());

        ResultInfo info=new ResultInfo();
        CircleService service=new CircleService();

        boolean flag=service.newCircle(circle);


        if (flag){
            info.setCode(1);
            info.setMsg("创建圈子成功");
            info.setCount(1);
        }else {
            info.setCode(0);
            info.setMsg("创建圈子失败");
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
