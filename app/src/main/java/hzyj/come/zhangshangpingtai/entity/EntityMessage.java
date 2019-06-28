package hzyj.come.zhangshangpingtai.entity;

import java.util.ArrayList;

/**
 * Created by EverGlow on 2019/1/9 15:42
 */

public class EntityMessage {
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

    public ArrayList<MessageInfo> getResult() {
        return result;
    }

    private ArrayList<MessageInfo> result;

    public static class MessageInfo {


        private String content;
        private String id;
        private String title;
        private String state;
        private String type;
        private String date;
        private String publisher;

        
        public String getContent() {
            return content == null ? "" : content;
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public String getState() {
            return state == null ? "" : state;
        }

        public String getType() {
            return type == null ? "" : type;
        }

        public String getDate() {
            return date == null ? "" : date;
        }

        public String getPublisher() {
            return publisher == null ? "" : publisher;
        }


    }
}
