package whu.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article implements Serializable {
    private int articleID;
    private String title;
    private String author;
    private String nickName;
    private String avatarPath;
    private String digest;
    private String tag;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private int thumbUpNum;
    private String parentTitle;
    private String parentTitleImagePath;

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getThumbUpNum() {
        return thumbUpNum;
    }

    public void setThumbUpNum(int thumbUpNum) {
        this.thumbUpNum = thumbUpNum;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public String getParentTitleImagePath() {
        return parentTitleImagePath;
    }

    public void setParentTitleImagePath(String parentTitleImagePath) {
        this.parentTitleImagePath = parentTitleImagePath;
    }


    @Override
    public String toString() {

        return "Article{" +
                "articleID=" + articleID +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", digest='" + digest + '\'' +
                ", tag='" + tag + '\'' +
                ", updateTime=" + updateTime +
                ", thumbUpNum=" + thumbUpNum +
                ", parentTitle='" + parentTitle + '\'' +
                ", parentTitleImagePath='" + parentTitleImagePath + '\'' +
                '}';
    }
}
