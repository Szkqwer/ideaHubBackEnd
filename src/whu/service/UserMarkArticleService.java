package whu.service;

import whu.beans.UserMarkArticle;
import whu.dao.UserMarkArticleDao;

public class UserMarkArticleService {
    private UserMarkArticleDao userMarkArticleDao = new UserMarkArticleDao();

    public boolean addMark(UserMarkArticle userMarkArticle){
        return userMarkArticleDao.addMarkRecord(userMarkArticle.getUserID(), userMarkArticle.getArticleID());
    }
}
