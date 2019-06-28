package hzyj.come.zhangshangpingtai.mvp.login;


import android.content.Intent;
import android.text.TextUtils;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import java.util.HashMap;

import javax.inject.Inject;

import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.MyApplication;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.CommonPreferences;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.https.okhttp.GsonRequest;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.mvp.ui.activity.MainActivity;

/**
 * Created by EverGlow on 2018/8/14 14:25
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.model, LoginContract.view> {

    @Inject
    GsonRequest mGsonRequest;

    @Inject
    public LoginPresenter(LoginContract.model model, LoginContract.view rootView) {
        super(model, rootView);
    }

    public void login(String account, String pwd) {

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            mRootView.showMessage(MyApplication.APP.getResources().getString(R.string.register_input_empty));
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put(NetWorkConstant.housenumber, account);
            map.put(NetWorkConstant.mobile, pwd);
            mRootView.showLoading();
            mGsonRequest.function(NetWorkConstant.app_login, map, EntitiyUser.class, new CallBack<EntitiyUser>() {
                @Override
                public void onResponse(EntitiyUser result) {
                    if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                        CommonPreferences preferences = new CommonPreferences(MyApplication.APP);
                        if (result.getResult() != null) {
                            preferences.setModel(result.getResult());
                            mRootView.launchActivity(new Intent(MyApplication.APP, MainActivity.class));
                            mRootView.killMyself();
                        }

                    }
                    mRootView.showMessage(result.getMessage());
                    mRootView.hideLoading();
                }

                @Override
                public void onFailure(String error) {
                    ToastUtil.showMessage(error);
                    mRootView.hideLoading();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGsonRequest = null;
    }
}
