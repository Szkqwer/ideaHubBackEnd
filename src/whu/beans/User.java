package whu.beans;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable {
    private int userID;//用户id
    private String userName;//用户名，账号
    private String password;//密码
    private String createdProject;
    private String joinedProjects;
    private String friends;//手机号
    private String points;//邮箱

    public User() {
    }

    public User(int userID, String userName, String password, String createdProject, String joinedProjects, String friends, String points) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createdProject = createdProject;
        this.joinedProjects = joinedProjects;
        this.friends = friends;
        this.points = points;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedProject() {
        return createdProject;
    }

    public void setCreatedProject(String createdProject) {
        this.createdProject = createdProject;
    }

    public String getJoinedProjects() {
        return joinedProjects;
    }

    public void setJoinedProjects(String joinedProjects) {
        this.joinedProjects = joinedProjects;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createdProject='" + createdProject + '\'' +
                ", joinedProjects='" + joinedProjects + '\'' +
                ", friends='" + friends + '\'' +
                ", points='" + points + '\'' +
                '}';
    }
}
