package whu.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Circle implements Serializable {
    private int circleID;
    private String name;
    private String circleLeaderNickName;
    private int circleLeaderId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date setupTime;

    private String digest;
    private String imagePath;
    private int numberCount;
    private String tag;
    private int articleCount;

    public int getCircleID() {
        return circleID;
    }

    public void setCircleID(int circleID) {
        this.circleID = circleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCircleLeaderNickName() {
        return circleLeaderNickName;
    }

    public void setCircleLeaderNickName(String circleLeaderNickName) {
        this.circleLeaderNickName = circleLeaderNickName;
    }

    public int getCircleLeaderId() {
        return circleLeaderId;
    }

    public void setCircleLeaderId(int circleLeaderId) {
        this.circleLeaderId = circleLeaderId;
    }

    public Date getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Date setupTime) {
        this.setupTime = setupTime;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "circleID=" + circleID +
                ", name='" + name + '\'' +
                ", circleLeaderNickName='" + circleLeaderNickName + '\'' +
                ", circleLeaderId=" + circleLeaderId +
                ", setupTime=" + setupTime +
                ", digest='" + digest + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", numberCount=" + numberCount +
                ", tag='" + tag + '\'' +
                ", articleCount=" + articleCount +
                '}';
    }
}
