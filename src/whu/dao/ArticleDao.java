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
        String sql1="select count(*) from article where 1=1 ";
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
}
