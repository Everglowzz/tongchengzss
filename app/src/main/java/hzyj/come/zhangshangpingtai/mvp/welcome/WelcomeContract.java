package hzyj.come.zhangshangpingtai.mvp.welcome;

import android.content.Intent;

import hzyj.come.zhangshangpingtai.interfaceCallBack.Action;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WelcomeContract {
    interface View  {
        
    }

    interface  Presenter  {
       void loginStatus(Action<Intent> action);
    }
}
