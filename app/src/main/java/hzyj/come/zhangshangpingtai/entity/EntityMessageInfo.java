package hzyj.come.zhangshangpingtai.entity;

/**
 * Created by EverGlow on 2019/1/9 15:42
 */

public class EntityMessageInfo {
    //        "chargeStaff": "admin",
//            "chargePaid": "929.45",
//            "updatetime": "2018-09-27 10:25:40",
//            "ifVacancy": "否",
//            "code": "2018000400",
//            "chargeAccunts": "929.45",
//            "chargeCode": "0303"
    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public EntityMessage.MessageInfo getResult() {
        return result;
    }

    private EntityMessage.MessageInfo result;

}
