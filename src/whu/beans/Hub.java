package whu.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Hub implements Serializable {
    private int hubID;
    private String hubName;
    private String hubLeader;
    private int memberCount;
    private String tag;
    private int level;
    private String administrators;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAdministrators() {
        return administrators;
    }

    public void setAdministrators(String administrators) {
        this.administrators = administrators;
    }

    public int getHubID() {
        return hubID;
    }

    public void setHubID(int hubID) {
        this.hubID = hubID;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public String getHubLeader() {
        return hubLeader;
    }

    public void setHubLeader(String hubLeader) {
        this.hubLeader = hubLeader;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Date setupTime) {
        this.setupTime = setupTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date setupTime;

    public Hub() {
    }

    @Override
    public String toString() {

        return "Hub{" +
                "name=" + hubName + '\''+
                ", hubID='" + hubID  +
                ", hubLeader='" + hubLeader + '\'' +
                ", setupTime='" + setupTime  +
                ", memberCount='" + memberCount  +
                ", tag='" + tag + '\'' +
                '}';
    }
}
