package hzyj.come.zhangshangpingtai.entity;

import java.util.ArrayList;

/**
 * Created by EverGlow on 2018/8/10 14:49
 */

public    class EntityBase   {
    
    private int code;

    private String message;
    
    
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message==null?"":message;
    }
    
    private  EntitiyUser appUsers;

    public EntitiyUser getAppUsers() {
        return appUsers == null ? new EntitiyUser():appUsers;
    }
    
    private ArrayList<appShippingAddress> appShippingAddressList;

    public ArrayList<appShippingAddress> getAppShippingAddressList() {
        return appShippingAddressList;
    }
}
