package whu.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.utils.JDBCUtils;

public class UserShareArticleDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    public boolean addShareRecord(int userID, int articleID, int targetCircleID, int targetUserID){
        String sql = "insert into user_share_article (userID, articleID, targetCircleID, targetUserID) values (?, ?, ?, ?)";
        try {
            return template.update(sql, userID, articleID, targetCircleID, targetUserID)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
