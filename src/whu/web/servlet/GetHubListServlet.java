package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import whu.beans.*;
import whu.service.ArticleService;
import whu.service.CircleService;
import whu.service.HubService;
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
@WebServlet("/circlePage/getCircleList")
public class GetHubListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type= request.getParameter("type");
        String size= request.getParameter("size"); //每页大小

/*        HttpSession session = request.getSession();
        User u=(User)session.getAttribute("user");
        int userID=u.getUserID();*/




        int pageSize=0; //每页显示条数
        if (size!=null&&size.length()>0){
            pageSize=Integer.parseInt(size);
        }else {
            pageSize=5;
        }

        CircleService service=new CircleService();
        PageBean<Circle> pb = service.getCircleList(type,pageSize);

        ResultInfo info=new ResultInfo();
        if (pb.getList()!=null&&pb.getList().size()!=0){
            info.setCode(1);
            info.setMsg("获取圈子列表成功");
            info.setCount(pb.getCount());
            info.setData(pb.getList());
        }else {
            info.setCode(0);
            info.setMsg("获取圈子列表失败");
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
