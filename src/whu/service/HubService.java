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

    public boolean joinRequest(String hubId, String userId) {
        int num=0;
        try {
            num=dao.joinRequest(Integer.parseInt(hubId),Integer.parseInt(userId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==0){
            return false;
        }else {
            return true;
        }
    }

    public boolean report(String hubId,String userId,String report){
        int num=0;
        try {
            num=dao.report(Integer.parseInt(hubId),Integer.parseInt(userId),report);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==0){
            return false;
        }else {
            return true;
        }

    }
    public boolean markhub(String userId,String hubId){
        int num=0;
        try {
            num=dao.markhub(Integer.parseInt(hubId),Integer.parseInt(userId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==0){
            return false;
        }else {
            return true;
        }


    }
    public boolean shareHub( String userId, String targetId,int sign) {
        int num=0;
        try {
            num=dao.shareHub(Integer.parseInt(userId),Integer.parseInt(targetId),sign);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (num==0){
            return false;
        }else {
            return true;
        }
    }
}
