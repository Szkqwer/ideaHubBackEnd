package whu.service;

import whu.beans.Circle;
import whu.beans.PageBean;
import whu.dao.CircleDao;

public class CircleService {
    CircleDao dao=new CircleDao();
    public boolean newCircle(Circle circle) {
        return dao.newCircle(circle);
    }

    public PageBean<Circle> getRecommendCircleList(String type, int pageSize) {
        return dao.GetRecommendCircleListPageQuery(type,pageSize);
    }

    public PageBean<Circle> getCircleList(String type, int pageSize) {
        return dao.GetCircleListPageQuery(type,pageSize);
    }

    public Circle findCircleById(String circleID) {
        return dao.findCircleById(Integer.parseInt(circleID));
    }
}
