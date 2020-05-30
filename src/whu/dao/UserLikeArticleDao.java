package whu.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import whu.utils.JDBCUtils;


public class UserLikeArticleDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    public boolean addLikeRecord(int userID, int articleID){
        String sql = "insert into user_like_article (userID, articleID) values (?, ?)";
        try {
            return template.update(sql, userID, articleID)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
