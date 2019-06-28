package hzyj.come.zhangshangpingtai.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import hzyj.come.zhangshangpingtai.mvp.register.RegisterContract;
import hzyj.come.zhangshangpingtai.mvp.register.RegisterModel;

/**
 * Created by EverGlow on 2018/8/10 15:45
 */

@Module
public    class RegisterModule {
    
    RegisterContract.View mView;
 
    public RegisterModule(RegisterContract.View view) {
        mView = view;
    }
    
    @ActivityScope
    @Provides
    RegisterContract.View provideRegisterView(){
        return mView;
    }
    
    @ActivityScope
    @Provides
    RegisterContract.Model provideRegisterModel(RegisterModel model){
        return  model;
    }
    
    
  
}
