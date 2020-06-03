package whu.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import whu.beans.Comments;
import whu.beans.ResultInfo;
import whu.beans.User;
import whu.service.ArticleService;
import whu.service.CommentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@WebServlet("/homePage/addComment")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User tempUser = (User) session.getAttribute("user");
        String userID=String.valueOf(tempUser.getUserID());


        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> set = parameterMap.keySet();
        for (String s : set) {
            System.out.println(s+"  "+parameterMap.get(s)[0]);
        }
        Comments comments=new Comments();
        try {
            BeanUtils.populate(comments,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        CommentsService service=new CommentsService();
        ResultInfo info=new ResultInfo();
        boolean flag=service.addComment(comments,Integer.parseInt(userID));
        if (flag){
            info.setCode(1);
            info.setMsg("评论成功");
        }else {
            info.setCode(0);
            info.setMsg("评论失败");
        }

        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //字符流写回
        System.out.println(json);
    }
}
