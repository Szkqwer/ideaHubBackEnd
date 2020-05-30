package whu.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.beans.User;
import whu.utils.JDBCUtils;

public class UserDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    public User findUserById(int id){
        User user=null;
        try {
            String sql="select * from users  where userID = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
