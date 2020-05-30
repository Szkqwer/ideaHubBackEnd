package whu.service;

import whu.beans.User;
import whu.dao.UserDao;

public class UserService {
    private UserDao dao=new UserDao();

    public User findUserById(String id){
        if (id!=null&&id.length()!=0&Integer.parseInt(id)>0){
            return dao.findUserById(Integer.parseInt(id));
        }
        return null;
    }
}
