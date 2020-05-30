package whu.beans;

import java.io.Serializable;

/**
 * 点赞
 */
public class UserLikeArticle implements Serializable{
    private int recordID;
    private int userID;
    private int articleID;

    public UserLikeArticle(int userID, int articleID) {
        this.userID = userID;
        this.articleID = articleID;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    @Override
    public String toString() {
        return "UserLikeArticle{" +
                "recordID=" + recordID +
                ", userID=" + userID +
                ", articleID=" + articleID +
                '}';
    }
}
