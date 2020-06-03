package whu.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.beans.Article;
import whu.beans.Circle;
import whu.beans.PageBean;
import whu.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class CircleDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    public boolean newCircle(Circle circle) {
        String sql="insert into circle(name, digest, imagePath, circleLeaderNickName, circleLeaderId, setupTime) " +
                "values(?, ?, ?, ?, ?, ?)";
        try {
            return template.update(sql,circle.getName(),circle.getDigest(),circle.getImagePath(),circle.getCircleLeaderNickName(),circle.getCircleLeaderId(),circle.getSetupTime())>0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PageBean<Circle> GetRecommendCircleListPageQuery(String type, int pageSize) {
        String sql="SELECT * FROM circle where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        PageBean<Circle> pb=new PageBean();
        List params=new ArrayList();//条件集合
        //判断参数是否有值

        if (type!=null&&type.length()!=0){
            sb.append(" and tag = ? ");
            params.add(type);//添加问号对应的值
        }
        sb.append(" ORDER BY numberCount DESC");
        sb.append(" limit 0 , ? ");
        params.add(pageSize);

        sql=sb.toString();


        String sql2="SELECT count(*) FROM circle ";
        int total= 0;
        if (type==null){
            total=template.queryForObject(sql2,Integer.class);
        }else {
            sql2+=" where tag= ?";
            total=template.queryForObject(sql2,Integer.class,type);
        }

        if (total==0){
            pb.setCount(0);
        }else if (total>=pageSize){
            pb.setCount(pageSize);
        }else {
            pb.setCount(total);
        }


        try {
            pb.setList(template.query(sql,new BeanPropertyRowMapper<Circle>(Circle.class),params.toArray()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return pb;
    }

    public PageBean<Circle> GetCircleListPageQuery(String type, int pageSize) {

        String sql="SELECT * FROM circle where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        PageBean<Circle> pb=new PageBean();
        List params=new ArrayList();//条件集合
        //判断参数是否有值

        if (type!=null&&type.length()!=0){
            sb.append(" and tag = ? ");
            params.add(type);//添加问号对应的值
        }

        sb.append(" limit 0 , ? ");
        params.add(pageSize);

        sql=sb.toString();


        String sql2="SELECT count(*) FROM circle ";
        int total= 0;
        if (type==null){
            total=template.queryForObject(sql2,Integer.class);
        }else {
            sql2+=" where tag= ?";
            total=template.queryForObject(sql2,Integer.class,type);
        }

        if (total==0){
            pb.setCount(0);
        }else if (total>=pageSize){
            pb.setCount(pageSize);
        }else {
            pb.setCount(total);
        }


        try {
            pb.setList(template.query(sql,new BeanPropertyRowMapper<Circle>(Circle.class),params.toArray()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return pb;
    }

    public Circle findCircleById(int circleID) {
        String sql="select * from circle where circleID = ?";
        Circle circle=null;
        try {
            circle=template.queryForObject(sql,new BeanPropertyRowMapper<>(Circle.class),circleID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return circle;
    }
}
