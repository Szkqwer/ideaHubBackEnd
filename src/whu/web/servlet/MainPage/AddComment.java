package whu.web.servlet.MainPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import whu.beans.Article;
import whu.beans.Comment;
import whu.beans.ResultInfo;
import whu.beans.User;
import whu.service.ArticleService;
import whu.service.CommentService;
import whu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 添加评论
 */
@WebServlet("/mainPage/addComment")
public class AddComment extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将前端传递的参数剥离
        // request.getParameter此方法要求前端提交参数时的编码格式为application/x-www-form-urlencoded
        // 如果前端提交参数时的编码格式为application/json或multipart/form-data则不能被识别
        String articleID = request.getParameter("articleId");
        String content = request.getParameter("content");
        String postParentID = request.getParameter("parentId");


//        String userID = request.getParameter("userId");
        HttpSession session = request.getSession();
        User tempUser = (User) session.getAttribute("user");
        String userID=String.valueOf(tempUser.getUserID());

        // 查询文章
        ArticleService articleService = new ArticleService();
        Article article =articleService.findArticleById(articleID);
        // 查询用户
        UserService userService = new UserService();
        User user = userService.findUserById(userID);
        // 默认的parentId为-1，如果不传递，则默认为对文章的直接评论
        int parentID=-1;
        if (postParentID!=null&&postParentID.length()>0&&Integer.parseInt(postParentID)>0) {
            parentID = Integer.parseInt(postParentID);
        }
        // 返回信息的初始化
        ResultInfo info=new ResultInfo();
        // 用户存在且文章存在且内容不为空
        if (article != null && user != null && content != null && content.length()>0){
            // 生成评论实例，添加到数据库
            Comment comment = new Comment(Integer.parseInt(userID), content,parentID, Integer.parseInt(articleID));
            CommentService commentService = new CommentService();
            if(commentService.addComment(comment)){
                info.setCode(1);
                info.setMsg("添加评论成功");
                info.setCount(0);
                info.setData(null);
            }else {
                info.setCode(0);
                info.setMsg("评论失败，parentID不存在");
            }
        }else {
            info.setCode(0);
            info.setMsg("评论失败，不存在该用户或内容为空或原评论已被删除");
        }

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
        System.out.println(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
