package whu.service;

import whu.beans.Article;
import whu.beans.Hub;
import whu.beans.PageBean;
import whu.dao.HubDao;

import java.util.ArrayList;
import java.util.List;

public class HubService {
    HubDao dao = new HubDao();
    public PageBean pageQuery(int currentPage, String type, int pageSize, String tag, String userId) {
        List<Hub> hubsList=new ArrayList<>();
        int start=(currentPage-1)*pageSize;
        return dao.findByPage(type,start,pageSize,tag,userId);
    }

    public Hub getHubDetail(String hubId) {
        int id=Integer.parseInt(String.valueOf(hubId));
        Hub hub=null;
        hub=dao.getHubDetail(id);


        return hub;
    }

    public boolean joinRequest(String circleID, String userId) {
        try {
            return dao.joinRequest(Integer.parseInt(circleID),Integer.parseInt(userId))>0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean report(String circleID,String userId,String report){
        try {
            return dao.report(Integer.parseInt(circleID),Integer.parseInt(userId),report)>0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }


    }
    public boolean markhub(String userId,String circleID){
        try {
            return dao.markhub(Integer.parseInt(circleID),Integer.parseInt(userId))>0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }



    }
    public boolean shareHub( String userId, String circleId,String targetId) {

        try {
            return dao.shareHub(Integer.parseInt(userId),Integer.parseInt(circleId),Integer.parseInt(targetId))>0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

    }
}
