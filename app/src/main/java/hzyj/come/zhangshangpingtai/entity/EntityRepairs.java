package hzyj.come.zhangshangpingtai.entity;

import java.util.ArrayList;

/**
 * Created by EverGlow on 2019/1/9 15:42
 */

public class EntityRepairs {
 
    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public ArrayList<RepairsInfo> getResult() {
        return result;
    }

    private ArrayList<RepairsInfo> result;

    public static class RepairsInfo {
        
//         "materialsCost": "100",
//                 "remark": "厕所马桶堵了",
//                 "laborCost": "100",
//                 "orderDate": 1574352000000,
//                 "demandType": "私人",
//                 "demandDate": -28800000,
//                 "processState": "未处理",
//                 "clientType": "f29149d2-a108-40b0-b695-8025283235a9"
//        
        private String materialsCost;
        private String remark;
        private String laborCost;
        private String orderDate;
        private String demandType;
        private String demandDate;
        private String processState;
        private String clientType;

        public String getMaterialsCost() {
            return materialsCost == null ? "" : materialsCost;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public String getLaborCost() {
            return laborCost == null ? "" : laborCost;
        }

        public String getOrderDate() {
            return orderDate == null ? "" : orderDate;
        }

        public String getDemandType() {
            return demandType == null ? "" : demandType;
        }

        public String getDemandDate() {
            return demandDate == null ? "" : demandDate;
        }

        public String getProcessState() {
            return processState == null ? "" : processState;
        }

        public String getClientType() {
            return clientType == null ? "" : clientType;
        }
    }
}
