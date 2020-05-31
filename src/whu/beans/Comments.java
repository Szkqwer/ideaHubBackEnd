package whu.beans;

import java.io.Serializable;

/**
 * 评论实体类
 */
public class Comments implements Serializable {
    private int commentID;
    private int articleID;
    private int userID;
    private String content;
    private int parentID;


    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentID=" + commentID +
                ", articleID=" + articleID +
                ", userID=" + userID +
                ", content='" + content + '\'' +
                ", parentID=" + parentID +
                '}';
    }
}
