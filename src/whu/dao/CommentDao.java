package whu.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.beans.Comment;
import whu.utils.JDBCUtils;

public class CommentDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    public boolean addComment(int userID, String content, int parentID, int articleID){
        String sql = "insert into comments (userID, content, parentID, articleID) values (?, ?, ?, ?)";
        try {
            return template.update(sql, userID, content, parentID, articleID)>0;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
