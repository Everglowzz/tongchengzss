package hzyj.come.zhangshangpingtai.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import hzyj.come.zhangshangpingtai.di.module.MainModule;
import hzyj.come.zhangshangpingtai.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}