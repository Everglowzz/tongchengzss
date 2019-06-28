package hzyj.come.zhangshangpingtai.entity;

/**
 * Created by Youga on 2015/7/1.
 */
public class EntityInform {
    private int code;
    private String message;
    private String result;

    public int getCode() {
        return  code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public String getResult() {
        return result == null ? "" : result;
    }
}
