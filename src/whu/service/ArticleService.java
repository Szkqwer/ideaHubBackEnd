package whu.service;

import whu.beans.Article;
import whu.beans.PageBean;
import whu.dao.ArticleDao;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    ArticleDao dao=new ArticleDao();
    public PageBean pageQuery(int currentPage, String type, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return dao.findByPage(type,start,pageSize);
    }

    public PageBean pageQuery(int userID,int currentPage, String type, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return dao.findByPage(userID,type,start,pageSize);
    }

    public Article findArticleById(String articleID) {
        if (articleID!=null&&articleID.length()!=0&Integer.parseInt(articleID)>0){
            return dao.findArticleById(Integer.parseInt(articleID));
        }
        return null;
    }

    public boolean thumbUp(int id,String articleID) {
        boolean flag=false;
        int num=0;
        try {
            num=dao.thumbUp(Integer.parseInt(articleID),id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==1){
            flag=true;
        }
        return flag;
    }

    public PageBean<Article> getPublishListPageQuery(int userID, int currentPage, String type, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return dao.getPublishListPageQuery(userID,type,start,pageSize);
    }

    public PageBean<Article> GetRecommendArticleListPageQuery(int currentPage, String type, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return dao.GetRecommendArticleListPageQuery(type,start,pageSize);
    }
}
