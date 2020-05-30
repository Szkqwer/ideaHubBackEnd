package whu.service;

import whu.beans.UserShareArticle;
import whu.dao.UserShareArticleDao;
public class UserShareArticleService {
    private UserShareArticleDao useShareArticleDao = new UserShareArticleDao();

    public boolean addShare(UserShareArticle userShareArticle){
        return useShareArticleDao.addShareRecord(userShareArticle.getUserID(), userShareArticle.getArticleID(), userShareArticle.getTargetCircleID(), userShareArticle.getTargetUserID());
    }
}
