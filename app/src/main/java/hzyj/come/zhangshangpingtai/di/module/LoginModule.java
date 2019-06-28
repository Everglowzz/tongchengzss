package hzyj.come.zhangshangpingtai.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import hzyj.come.zhangshangpingtai.mvp.login.LoginContract;
import hzyj.come.zhangshangpingtai.mvp.login.LoginModel;

/**
 * Created by EverGlow on 2018/8/14 14:41
 */
@Module
public    class LoginModule   {
    LoginContract.view mView;

    public LoginModule(LoginContract.view view) {
        mView = view;
    }
    @ActivityScope
    @Provides
    LoginContract.view provideLoginView() {
      return  mView;  
    }
    @ActivityScope
    @Provides
    LoginContract.model provideLoginModel(LoginModel model){
        return  model;
    }
}
