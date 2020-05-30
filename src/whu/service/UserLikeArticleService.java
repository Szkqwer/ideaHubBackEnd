package whu.service;

import whu.beans.UserLikeArticle;
import whu.dao.UserLikeArticleDao;

public class UserLikeArticleService {
    private UserLikeArticleDao userLikeArticleDao = new UserLikeArticleDao();

    public boolean addLike(UserLikeArticle userLikeArticle){
        return userLikeArticleDao.addLikeRecord(userLikeArticle.getUserID(), userLikeArticle.getArticleID());
    }
}
