package hzyj.come.zhangshangpingtai.entity;

/**
 * Created by Youga on 2015/7/1.
 */
public class EntityVersion {
    private int code;
    private String message;
    private VersionInfo result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public VersionInfo getResult() {
        return result;
    }

    public static class VersionInfo {
        private String versionsExplain;
        private String path;
        private String versionsName;
        private int versionsCode;
        private int ifUpdate;

        public int getIfUpdate() {
            return ifUpdate;
        }

        public String getVersionsExplain() {
            return versionsExplain == null ? "" : versionsExplain;
        }

        public String getPath() {
            return path == null ? "" : path;
        }

        public String getVersionsName() {
            return versionsName == null ? "" : versionsName;
        }

        public int getVersionsCode() {
            return versionsCode;
        }
    }

}
