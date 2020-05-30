package whu.beans;

import java.io.Serializable;

/**
 * 用户收藏表
 */
public class UserMarkArticle implements Serializable {
    private int userArticleID;
    private int userID;
    private int articleID;

    public UserMarkArticle(int userID, int articleId) {
        this.userID = userID;
        this.articleID = articleId;
    }

    public int getUserArticleID() {
        return userArticleID;
    }

    public void setUserArticleID(int userArticleID) {
        this.userArticleID = userArticleID;
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

    public void setArticleID(int articleId) {
        this.articleID = articleId;
    }

    @Override
    public String toString() {
        return "UserMarkArticle{" +
                "userArticleID=" + userArticleID +
                ", userID=" + userID +
                ", articleId=" + articleID +
                '}';
    }
}
