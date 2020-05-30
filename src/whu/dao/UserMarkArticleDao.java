package whu.dao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.utils.JDBCUtils;

public class UserMarkArticleDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    public boolean addMarkRecord(int userID, int articleID){
        String sql = "insert into user_collect_article (userID, articleID) values (?, ?)";
        try {
            return template.update(sql, userID, articleID)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
