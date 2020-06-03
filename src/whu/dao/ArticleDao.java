package whu.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import whu.beans.Article;
import whu.beans.PageBean;
import whu.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    public PageBean findByPage(String type, int start, int pageSize) {
        //String sql="select * from article where tag=? limit ? , ?";
        String sql="select * from article where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        PageBean<Article> pb=new PageBean();
        List params=new ArrayList();//条件集合
        //判断参数是否有值
        if (type!=null){
            sb.append(" and tag = ? ");
            params.add(type);//添加问号对应的值
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);

        sql=sb.toString();


        String sql2="select count(*) from article";
        int total= 0;
        if (type==null){
            total=template.queryForObject(sql2,Integer.class);
        }else {
            sql2+=" where tag = ?";
            total=template.queryForObject(sql2,Integer.class,type);
        }
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
            pb.setList(template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return pb;
    }

    public PageBean findByPage(int userID,String type, int start, int pageSize) {
        //SELECT * FROM article WHERE articleID IN (SELECT articleID FROM user_likes_article WHERE userID=1) AND tag='aaa' LIMIT 0,3
        String sql="select * from article where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        PageBean<Article> pb=new PageBean();
        List params=new ArrayList();//条件集合
        //判断参数是否有值
        if (userID!=0){
            sb.append(" and articleID IN (SELECT articleID FROM user_likes_article WHERE userID= ? )");
            params.add(userID);
        }
        if (type!=null){
            sb.append(" and tag = ? ");
            params.add(type);//添加问号对应的值
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);

        sql=sb.toString();


        String sql2="SELECT count(*) FROM article WHERE articleID IN (SELECT articleID FROM user_likes_article WHERE userID = ?) ";
        int total= 0;
        if (type==null){
            total=template.queryForObject(sql2,Integer.class,userID);
        }else {
            sql2+=" AND tag= ?";
            total=template.queryForObject(sql2,Integer.class,userID,type);
        }

        //total=template.queryForObject(sql2,Integer.class,userID,type);
        System.out.println(total);
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
            pb.setList(template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return pb;
    }

    public Article findArticleById(int articleID) {
        Article article=null;
        String sql="select * from article where articleID = ?";
        try {
            article=template.queryForObject(sql,new BeanPropertyRowMapper<Article>(Article.class),articleID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return article;
    }

    public int thumbUp(int articleID,int id) {
        Article article=null;
        String sql="update article set thumbUpNum = thumbUpNum+1 where articleID = ?";
        String sql1="insert user_likes_article(userID,articleID) values(?, ?)";
        int num=0;
        try {
            num=template.update(sql1,id,articleID);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (num!=0){
            try {
                template.update(sql,articleID);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        return num;
    }

    public PageBean<Article> getPublishListPageQuery(int userID, String type, int start, int pageSize) {
        //SELECT * FROM article WHERE articleID IN (SELECT articleID FROM user_likes_article WHERE userID=1) AND tag='aaa' LIMIT 0,3
        String sql="select * from article where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        PageBean<Article> pb=new PageBean();
        List params=new ArrayList();//条件集合
        //判断参数是否有值
        if (userID!=0){
            sb.append(" and nickname = (SELECT nickname FROM users WHERE userID= ? )");
            params.add(userID);
        }
        if (type!=null&&type.length()!=0){
            sb.append(" and tag = ? ");
            params.add(type);//添加问号对应的值
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);

        sql=sb.toString();


        String sql2="SELECT count(*) FROM article WHERE nickname = (SELECT nickname FROM users WHERE userID= ? ) ";
        int total= 0;
        if (type==null){
            total=template.queryForObject(sql2,Integer.class,userID);
        }else {
            sql2+=" AND tag= ?";
            total=template.queryForObject(sql2,Integer.class,userID,type);
        }

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
            pb.setList(template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return pb;
    }

    public PageBean<Article> GetRecommendArticleListPageQuery(String type, int start, int pageSize) {
        //SELECT * FROM article where tag=type ORDER BY thumbUpNum DESC limit 0,3
        String sql="SELECT * FROM article where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);

        PageBean<Article> pb=new PageBean();
        List params=new ArrayList();//条件集合
        //判断参数是否有值

        if (type!=null&&type.length()!=0){
            sb.append(" and tag = ? ");
            params.add(type);//添加问号对应的值
        }
        sb.append(" ORDER BY thumbUpNum DESC ");
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);

        sql=sb.toString();


        String sql2="SELECT count(*) FROM article ";
        int total= 0;
        if (type==null){
            total=template.queryForObject(sql2,Integer.class);
        }else {
            sql2+=" where tag= ?";
            total=template.queryForObject(sql2,Integer.class,type);
        }

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
            pb.setList(template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return pb;
    }
}
