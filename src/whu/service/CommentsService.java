package whu.service;

import whu.beans.Comments;
import whu.dao.CommentsDao;

public class CommentsService {
    CommentsDao dao=new CommentsDao();
    public boolean addComment(Comments comments){
        int num=dao.addComment(comments);
        if (num==0){
            return false;
        }else {
            return true;
        }
    }
}
