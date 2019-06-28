package hzyj.come.zhangshangpingtai.mvp.gate;

/**
 * Created by EverGlow on 2018/8/14 15:27
 */

public  interface  GateContract     {
    
    interface view {
        void skipMain();
        void skipLogin();
        void skipWelcome(String url);
        void killSelf();
    }
    
    interface presenter {
        void judge();
    }
}
