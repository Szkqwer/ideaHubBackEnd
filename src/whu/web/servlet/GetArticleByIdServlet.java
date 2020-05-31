package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Article;
import whu.beans.ResultInfo;
import whu.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据id获取文章信息
 */
@WebServlet("/homePage/getArticleDetail")
public class GetArticleByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleID = request.getParameter("articleId");
        ArticleService service=new ArticleService();
        ResultInfo info=new ResultInfo();
        Article article =service.findArticleById(articleID);
        System.out.println(article.getUpdateTime());
        if (article!=null){
            //用户存在
            info.setCode(1);
            info.setMsg("获取文章详情成功");
            info.setCount(1);
            info.setData(article);
        }else {
            info.setCode(0);
            info.setMsg("获取文章详情失败");
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
