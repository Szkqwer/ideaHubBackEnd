package whu.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * 用于封装后端返回前端数据对象
 */
public class ResultInfo implements Serializable {
    private int code;//后端返回结果正常为1，发生异常返回0
    private Object data;//后端返回结果数据对象
    private int count;
    private String msg;//返回给前端的信息

    public ResultInfo() {
    }

    public ResultInfo(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
