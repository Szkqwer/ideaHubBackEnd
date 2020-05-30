package whu.service;

import whu.beans.Article;
import whu.beans.PageBean;
import whu.dao.ArticleDao;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    ArticleDao dao=new ArticleDao();
    public PageBean pageQuery(int currentPage, String type, int pageSize) {
        List<Article> articleList=new ArrayList<>();
        int start=(currentPage-1)*pageSize;
        return dao.findByPage(type,start,pageSize);
    }

    public Article findArticleById(String articleId) {
        if (articleId!=null&&articleId.length()!=0&Integer.parseInt(articleId)>0){
            return dao.findArticleById(Integer.parseInt(articleId));
        }
        return null;
    }
}
