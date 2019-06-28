package hzyj.come.zhangshangpingtai.mvp.register;
import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import hzyj.come.zhangshangpingtai.entity.EntityBase;

/**
 * Created by EverGlow on 2018/8/10 12:04
 */
@ActivityScope
public    class RegisterModel extends BaseModel  implements RegisterContract.Model  {

    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
 
    @Inject
    public RegisterModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public EntityBase regester(String account, String pwd) {
        return  null;
    }
}
