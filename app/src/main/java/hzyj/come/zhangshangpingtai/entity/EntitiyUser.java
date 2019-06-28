package hzyj.come.zhangshangpingtai.entity;

import java.io.Serializable;

/**
 * Created by EverGlow on 2018/8/10 14:48
 */

public    class EntitiyUser   implements Serializable {

    private int code;

    private String message;

    public int getCode() {
        return  code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getResult() {
        return result;
    }

    public void setResult(UserInfo result) {
        this.result = result;
    }

    private UserInfo result;
    
    public static class UserInfo implements Serializable {
        
        private String coverArea;
        private String buildName;
        private String houseName;
        private String ownname;
        private String mobile;
        private String ownInfoId;

        public String getOwnInfoId() {
            return ownInfoId == null ? "" : ownInfoId;
        }

        public void setOwnInfoId(String ownInfoId) {
            this.ownInfoId = ownInfoId;
        }

        public String getCoverArea() {
            return coverArea == null ? "" : coverArea;
        }

        public void setCoverArea(String coverArea) {
            this.coverArea = coverArea;
        }

        public String getBuildName() {
            return buildName == null ? "" : buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public String getHouseName() {
            return houseName == null ? "" : houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getOwnname() {
            return ownname == null ? "" : ownname;
        }

        public void setOwnname(String ownname) {
            this.ownname = ownname;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
