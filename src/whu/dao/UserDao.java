package whu.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.beans.User;
import whu.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    public User findUserById(int id){
        String sql="select * from users where userID = ?";
        User u=null;
        try {
            u=template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }

    public int markArticle(int articleID, int userID) {
        String sql="insert into user_collect_article(userID, articleID) values(?, ?)";
        int num=0;
        try {
            num=template.update(sql,userID,articleID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int shareArticle(int articleID, int userID, int targetID,int sign) {
        String sql;
        if (sign==1){
            sql="insert into user_share_article(userID, articleID,targetUserID) values(?, ?, ?)";
        }else {
            sql="insert into user_share_article(userID, articleID,targetCircleID) values(?, ?, ?)";
        }
        int num=0;
        try {
            num=template.update(sql,userID,articleID,targetID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    public User getPersonalInfo(int userID) {
        String sql="select * from users where userID = ?";
        User user=null;

        try {
            user=template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),userID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;

    }

    public List<Integer> getLikes(int id) {
        String sql="select articleID from user_like_article where userID = ?";
        List<Integer> list=new ArrayList<>();
        try {
            list=template.queryForList(sql,Integer.class,id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> getMarksArtic(int id) {
        String sql="select articleID from user_collect_article where userID = ?";
        List<Integer> list=new ArrayList<>();
        try {
            list=template.queryForList(sql,Integer.class,id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> getMarksHub(int id) {
        String sql="select circleID from user_collect_circle where userID = ?";
        List<Integer> list=new ArrayList<>();
        try {
            list=template.queryForList(sql,Integer.class,id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int editPersonalInfo(User u) {
        String sql="update users set nickName = ?, avatarPath = ?, sex = ?, slogan = ? where userID = ?";
        int num=0;
        try {
            num=template.update(sql,u.getNickName(),u.getAvatarPath(),u.getSex(),u.getSlogan(),u.getUserID());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int deleteArticle(int userID, int articleID) {
        String sql="delete from user_likes_article  where userID = ? and articleID = ?";
        int num=0;
        try {
            num=template.update(sql,userID,articleID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int deleteMarks(int userID,int editCircleID) {
        int num=0;
        try {
            String sql="delete from user_collect_circle  where userID = ? and circleID = ?";
            num=template.update(sql,userID,editCircleID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return num;
    }

    public boolean registerUser(int userID, String nickname, String password) {

        try {
            String sql="insert into users(userID,nickname,password)  values( ?, ?, ?)";
            return template.update(sql,userID,nickname,password)>0;
        } catch (DataAccessException e) {
            return false;
        }

    }

    public User loginUser(int userID, String password) {
        User user=null;
        try {
            String sql="select * from users where userID = ? and password = ?";
            user=template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),userID,password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
