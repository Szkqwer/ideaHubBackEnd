package whu.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 */
public class User implements Serializable {
    private int userID;
    private String nickName;
    private String avatarPath;
    private String sex;
    private String slogan;
    private int thumbUpNum;
    private int fansNum;
    private int ViewNum;
    private int level;
    private List<Integer> likes;
    private List<Integer> marksArtic;
    private List<Integer> marksHub;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getThumbUpNum() {
        return thumbUpNum;
    }

    public void setThumbUpNum(int thumbUpNum) {
        this.thumbUpNum = thumbUpNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getViewNum() {
        return ViewNum;
    }

    public void setViewNum(int viewNum) {
        ViewNum = viewNum;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Integer> getLikes() {
        return likes;
    }

    public void setLikes(List<Integer> likes) {
        this.likes = likes;
    }

    public List<Integer> getMarksArtic() {
        return marksArtic;
    }

    public void setMarksArtic(List<Integer> marksArtic) {
        this.marksArtic = marksArtic;
    }

    public List<Integer> getMarksHub() {
        return marksHub;
    }

    public void setMarksHub(List<Integer> marksHub) {
        this.marksHub = marksHub;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", nickName='" + nickName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", sex='" + sex + '\'' +
                ", slogan='" + slogan + '\'' +
                ", thumbUpNum=" + thumbUpNum +
                ", fansNum=" + fansNum +
                ", ViewNum=" + ViewNum +
                ", level=" + level +
                ", likes=" + likes +
                ", marksArtic=" + marksArtic +
                ", marksHub=" + marksHub +
                '}';
    }
}
