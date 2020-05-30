package whu.service;

import  whu.beans.Comment;
import whu.dao.CommentDao;

public class CommentService {
    private CommentDao commentDao = new CommentDao();

    public boolean addComment(Comment comment){
        return commentDao.addComment(comment.getUserID(), comment.getContent(),comment.getParentID(),comment.getArticleID());
    }
}
