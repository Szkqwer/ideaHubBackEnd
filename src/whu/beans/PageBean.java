package whu.beans;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {
    private List<T> list;//每页显示的数据集合
    private int count;//查询到的记录条数

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
