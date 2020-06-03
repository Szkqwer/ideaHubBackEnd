package whu.web.servlet.newFunction;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Article;
import whu.beans.Circle;
import whu.beans.PageBean;
import whu.beans.ResultInfo;
import whu.service.ArticleService;
import whu.service.CircleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/circlePage/getRecommendCircleList")
public class GetRecommendCircleListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type= request.getParameter("type");
        String size= request.getParameter("size"); //每页大小




        int pageSize=0; //每页显示条数
        if (size!=null&&size.length()>0){
            pageSize=Integer.parseInt(size);
        }else {
            pageSize=5;
        }

        CircleService service=new CircleService();
        PageBean<Circle> pb = service.getRecommendCircleList(type,pageSize);

        ResultInfo info=new ResultInfo();
        if (pb.getList()!=null&&pb.getList().size()!=0){
            info.setCode(1);
            info.setMsg("获取推荐圈子列表成功");
            info.setCount(pb.getCount());
            info.setData(pb.getList());
        }else {
            info.setCode(0);
            info.setMsg("获取推荐圈子列表失败");
        }



        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
    }
}
