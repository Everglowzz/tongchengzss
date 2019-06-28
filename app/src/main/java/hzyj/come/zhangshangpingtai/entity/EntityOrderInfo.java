package hzyj.come.zhangshangpingtai.entity;

import java.util.ArrayList;

/**
 * Created by EverGlow on 2019/1/9 15:42
 */

public class EntityOrderInfo {
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

    public ArrayList<OrderInfo> getResult() {
        return result;
    }

    private ArrayList<OrderInfo> result;

    public static class OrderInfo {
        public   OrderInfo(String alias){
            this.alias = alias;
        }
        private String chargeStaff;
        private String chargePaid;
        private String updatetime;
        private String ifVacancy;
        private String code;
        private String chargeAccunts;
        private String chargeCode;
        private String startTime;
        private String endTime;
        private String chargeUnitPrice;
        private String chargeName;
        private String num;
        private String id;
        private String alias;

        public String getId() {
            return id == null ? "" : id;
        }

        public String getAlias() {
            return alias == null ? "" : alias;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getChargeUnitPrice() {
            return chargeUnitPrice;
        }

        public String getChargeName() {
            return chargeName;
        }

        public String getNum() {
            return num;
        }

        public String getChargeStaff() {
            return chargeStaff == null ? "" : chargeStaff;
        }

        public void setChargeStaff(String chargeStaff) {
            this.chargeStaff = chargeStaff;
        }

        public String getChargePaid() {
            return chargePaid == null ? "" : chargePaid;
        }

        public void setChargePaid(String chargePaid) {
            this.chargePaid = chargePaid;
        }

        public String getUpdatetime() {
            return updatetime == null ? "" : updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getIfVacancy() {
            return ifVacancy == null ? "" : ifVacancy;
        }

        public void setIfVacancy(String ifVacancy) {
            this.ifVacancy = ifVacancy;
        }

        public String getCode() {
            return code == null ? "" : code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getChargeAccunts() {
            return chargeAccunts == null ? "" : chargeAccunts;
        }

        public void setChargeAccunts(String chargeAccunts) {
            this.chargeAccunts = chargeAccunts;
        }

        public String getChargeCode() {
            return chargeCode == null ? "" : chargeCode;
        }

        public void setChargeCode(String chargeCode) {
            this.chargeCode = chargeCode;
        }
    }
}
