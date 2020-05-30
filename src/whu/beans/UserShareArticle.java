package whu.beans;

import java.io.Serializable;
public class UserShareArticle implements Serializable{
    private int shareID;
    private int userID;
    private int articleID;
    // 也可以是targetHubID
    private int targetCircleID;
    private int targetUserID;

    public UserShareArticle(int userID, int articleID, int targetCircleID, int targetUserID){
        this.userID = userID;
        this.articleID = articleID;
        // this.targetHubID = targetHubID;
        this.targetCircleID = targetCircleID;
        this.targetUserID = targetUserID;
    }

    public int getShareID() {
        return shareID;
    }

    public void setShareID(int shareID) {
        this.shareID = shareID;
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

    public int getTargetCircleID() {
        return targetCircleID;
    }

    public void setTargetCircleID(int targetCircleID) {
        this.targetCircleID = targetCircleID;
    }

    public int getTargetUserID() {
        return targetUserID;
    }

    public void setTargetUserID(int targetUserID) {
        this.targetUserID = targetUserID;
    }

    @Override
    public String toString() {
        return "UserShareArticle{" +
                "shareID=" + shareID +
                ", userID=" + userID +
                ", articleID=" + articleID +
                ", targetCircleID=" + targetCircleID +
                ", targetUserID=" + targetUserID +
                '}';
    }
}
