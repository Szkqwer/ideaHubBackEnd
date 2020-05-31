package whu.web.servlet.MainPage;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.jetbrains.annotations.NotNull;
import whu.beans.Article;
import whu.beans.PageBean;
import whu.beans.ResultInfo;

import whu.service.ArticleService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 这个文件可以暂时不用，但不确定

/**
 * 获取文章列表
 */

@WebServlet("/mainPage/getArticleList")
public class GetArticleList extends HttpServlet{
    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page= request.getParameter("page"); //当前页数
        String type= request.getParameter("type");
        String size= request.getParameter("size"); //每页大小

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

        ArticleService service=new ArticleService();
        PageBean<Article> pb = service.pageQuery(currentPage,type,pageSize);

        ResultInfo info=new ResultInfo();
        if (pb.getList()!=null&&pb.getList().size()!=0){
            // 有文章内容
            info.setCode(1);
            info.setMsg("获取文章列表成功");
            info.setCount(pb.getCount());
            info.setData(pb.getList());
        }else {
            info.setCode(0);
            info.setMsg("获取文章列表失败");
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
