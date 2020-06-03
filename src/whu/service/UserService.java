package whu.service;

import whu.beans.User;
import whu.dao.UserDao;

public class UserService {
    UserDao dao=new UserDao();
    public boolean markArticle(String articleID, String userID) {
        int num=0;
        try {
            num=dao.markArticle(Integer.parseInt(articleID),Integer.parseInt(userID));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==0){
            return false;
        }else {
            return true;
        }
    }

    public boolean shareArticle(String articleID, String userID, String targetID,int sign) {
        int num=0;
        try {
            num=dao.shareArticle(Integer.parseInt(articleID),Integer.parseInt(userID),Integer.parseInt(targetID),sign);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==0){
            return false;
        }else {
            return true;
        }
    }

    public User getPersonalInfo(String userID) {
        int id=Integer.parseInt(userID);
        User user=null;
        user=dao.getPersonalInfo(id);
        user.setLikes(dao.getLikes(id));//点赞文章
        user.setMarksArtic(dao.getMarksArtic(id));//收藏文章
        user.setMarksHub(dao.getMarksHub(id));//收藏圈子

        return user;
    }

    public User editPersonalInfo(User u) {
        int num=0;
        num=dao.editPersonalInfo(u);
        if (num==0){
            return null;
        }

        User user=dao.findUserById(u.getUserID());
        return user;
    }

    public boolean deleteArticle(String userID, String articleID) {
        int num=0;
        try {
            num=dao.deleteArticle(Integer.parseInt(userID),Integer.parseInt(articleID));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==0){
            return false;
        }
        return true;
    }

    public boolean deleteMarks(String userID, String editCircleID) {
        int num=0;
        try {
            num=dao.deleteMarks(Integer.parseInt(userID),Integer.parseInt(editCircleID));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(num==0){
            return false;
        }

        return true;


    }


    public User findUserById(String id){
        return dao.findUserById(Integer.parseInt(id));
    }

    public boolean registerUser(int userId, String nickname, String password) {
        return dao.registerUser(userId,nickname,password);
    }

    public User loginUser(String userId, String password) {
        return dao.loginUser(Integer.parseInt(userId),password);
    }
}
