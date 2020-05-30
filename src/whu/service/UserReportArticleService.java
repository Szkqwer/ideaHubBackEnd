package whu.service;

import whu.beans.UserReportArticle;
import whu.dao.UserReportArticleDao;


public class UserReportArticleService {
    private UserReportArticleDao userReportArticleDao = new UserReportArticleDao();

    public boolean addReport(UserReportArticle userReportArticle){
        return userReportArticleDao.addReportRecord(userReportArticle.getArticleID(), userReportArticle.getRepotUserID(), userReportArticle.getReport());
    }
}
