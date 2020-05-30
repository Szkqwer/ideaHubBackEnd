package whu.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.beans.Article;
import whu.beans.Hub;
import whu.beans.PageBean;
import whu.beans.User;
import whu.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class HubDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    public PageBean findByPage(String type, int start, int pageSize, String tag, String userId) {
        //String sql="select * from article where tag=? limit ? , ?";
        String sql="select * from hub where 1=1 ";
        String sql1="select count(*) from hub where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        PageBean<Hub> pb=new PageBean();
        List params=new ArrayList();//条件集合
        //判断参数是否有值
        if (type!=null){
            sb.append(" and tag = ? ");
            params.add(type);//添加问号对应的值
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        params.add(tag);
        params.add(userId);

        sql=sb.toString();


        String sql2="select count(*) from hub";
        int total= 0;
        total=template.queryForObject(sql2,Integer.class);
        if (total==0){
            pb.setCount(0);
        }else if (total>=start+pageSize){
            pb.setCount(pageSize);
        }else if (total<=start){
            pb.setCount(0);
        }else {
            pb.setCount(total-start);
        }


        try {
            pb.setList(template.query(sql,new BeanPropertyRowMapper<Hub>(Hub.class),params.toArray()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return pb;
    }
    public Hub findHubById(int id){
        String sql="select * from users where hubId = ?";
        Hub u=null;
        try {
            u=template.queryForObject(sql,new BeanPropertyRowMapper<Hub>(Hub.class),id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }
    public Hub getHubDetail(int id){
        String sql="select * from hub where hubId = ?";
        Hub u=null;
        try {
            u=template.queryForObject(sql,new BeanPropertyRowMapper<Hub>(Hub.class),id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }

    public int joinRequest(int hubId,int userId){
        String sql;
        sql="insert into user_join_hub(hubId,userID) values(?, ?)";
        int num=0;
        try {
            num=template.update(sql,hubId,userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;

    }

    public int report(int hubId,int userId,String report){
        String sql;
        sql="insert into user_report(hubId,userID,report) values(?, ?, ?)";
        int num=0;
        try {
            num=template.update(sql,hubId,userId,report);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int markhub(int hubId,int userId){
        String sql;
        sql="insert into user_mark_hub(hubId,userID) values(?, ?)";
        int num=0;
        try {
            num=template.update(sql,hubId,userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int shareHub(int hubId, int targetId, int sign) {
        String sql;
        if (sign==1){
            sql="insert into user_share_hub(hubId, targetUserId) values(?, ?)";
        }else {
            sql="insert into user_share_hub(hubId, targetCircleId) values(?, ?)";
        }
        int num=0;
        try {
            num=template.update(sql,hubId,targetId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return num;
    }
}
