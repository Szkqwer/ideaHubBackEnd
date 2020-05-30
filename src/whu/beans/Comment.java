package whu.beans;

import java.io.Serializable;

public class Comment implements Serializable{
    private int commentID;
    private int userID;
    private String content;
    private int parentID;
    private int articleID;

    public Comment(int userID, String content, int parentID, int articleID){
        this.userID = userID;
        this.content = content;
        this.parentID = parentID;
        this.articleID = articleID;
    }
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
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

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", userID=" + userID +
                ", content='" + content + '\'' +
                ", parentID=" + parentID +
                ", articleID=" + articleID +
                '}';
    }
}
