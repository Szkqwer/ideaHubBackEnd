package whu.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.beans.Comments;
import whu.utils.JDBCUtils;

public class CommentsDao {
    JdbcTemplate template= new JdbcTemplate(JDBCUtils.getDataSource());
    public int addComment(Comments comments,int userID) {

        String sql="insert into comments(articleID, userID, content, parentID) values( ? , ? , ? , ?)";
        int num= 0;
        try {
            num = template.update(sql,comments.getArticleID(),userID,comments.getContent(),comments.getParentID());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }
}
