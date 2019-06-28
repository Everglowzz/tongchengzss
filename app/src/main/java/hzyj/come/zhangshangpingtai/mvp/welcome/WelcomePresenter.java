package hzyj.come.zhangshangpingtai.mvp.welcome;

import android.content.Intent;

import hzyj.come.zhangshangpingtai.app.MyApplication;
import hzyj.come.zhangshangpingtai.app.https.config.CommonPreferences;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.global.Constants;
import hzyj.come.zhangshangpingtai.interfaceCallBack.Action;
import hzyj.come.zhangshangpingtai.mvp.ui.activity.MainActivity;

import static hzyj.come.zhangshangpingtai.global.Constants.VERSION_PAIR;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WelcomePresenter  implements WelcomeContract.Presenter{
    
    public void loginStatus(Action<Intent> action) {
        Constants.spManager.setCommon(VERSION_PAIR[0], VERSION_PAIR[1]);
        CommonPreferences preferences = new CommonPreferences(MyApplication.APP);
        EntitiyUser user = preferences.getModel(EntitiyUser.class);
        if (user == null) {
            action.call(new Intent(MyApplication.getApplication(), MainActivity.class));
        } else {
            action.call(new Intent(MyApplication.getApplication(), MainActivity.class));
        }
    }
    
}
