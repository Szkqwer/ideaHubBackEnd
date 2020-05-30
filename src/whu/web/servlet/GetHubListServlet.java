package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import whu.beans.*;
import whu.service.ArticleService;
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
@WebServlet("/hubPage/getHubList")
public class GetHubListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page= request.getParameter("page"); //当前页数
        String type= request.getParameter("type");
        String size= request.getParameter("size"); //每页大小
        String tag=request.getParameter("tag");
        String userId=request.getParameter("userId");

        int currentPage=0; //当前页码，如果不传递，则默认为第一页
        if (page!=null&&page.length()>0&&Integer.parseInt(page)>0){
            currentPage=Integer.parseInt(page);
        }else {
            currentPage=1;
        }

        int pageSize=0; //每页显示条数
        if (size!=null&&size.length()>0){
            pageSize=Integer.parseInt(size);
        }else {
            pageSize=5;
        }

        HubService service=new HubService();
        PageBean<Hub> pb = service.pageQuery(currentPage,type,pageSize,tag,userId);

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
        System.out.println(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
