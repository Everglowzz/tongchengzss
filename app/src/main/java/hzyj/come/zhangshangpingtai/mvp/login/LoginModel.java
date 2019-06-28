package hzyj.come.zhangshangpingtai.mvp.login;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * Created by EverGlow on 2018/8/14 14:23
 */
@ActivityScope
public    class LoginModel extends BaseModel  implements LoginContract.model  {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void login() {

    }
}
