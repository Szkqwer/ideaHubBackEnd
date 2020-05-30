package whu.beans;

import java.io.Serializable;

public class UserReportArticle implements Serializable{
    private int recordID;
    private int articleID;
    private int repotUserID;
    private String report;

    public UserReportArticle(int articleID, int repotUserID, String report) {
        this.articleID = articleID;
        this.repotUserID = repotUserID;
        this.report = report;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getRepotUserID() {
        return repotUserID;
    }

    public void setRepotUserID(int repotUserID) {
        this.repotUserID = repotUserID;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "UserReportArticle{" +
                "recordID=" + recordID +
                ", articleID=" + articleID +
                ", repotUserID=" + repotUserID +
                ", report='" + report + '\'' +
                '}';
    }
}
