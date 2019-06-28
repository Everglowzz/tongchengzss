package hzyj.come.zhangshangpingtai.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.MyApplication;
import hzyj.come.zhangshangpingtai.app.https.config.CommonPreferences;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.copy.MapActivity;
import hzyj.come.zhangshangpingtai.copy.commentrecycle.RecycleActivity;
import hzyj.come.zhangshangpingtai.copy.message.MessageListActivity;
import hzyj.come.zhangshangpingtai.copy.suggest.SuggestActivity;
import hzyj.come.zhangshangpingtai.di.component.DaggerMainComponent;
import hzyj.come.zhangshangpingtai.di.module.MainModule;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.global.Constants;
import hzyj.come.zhangshangpingtai.mvp.contract.MainContract;
import hzyj.come.zhangshangpingtai.mvp.login.LoginActivity;
import hzyj.come.zhangshangpingtai.mvp.presenter.MainPresenter;
import hzyj.come.zhangshangpingtai.mvp.ui.fragment.HomeFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static hzyj.come.zhangshangpingtai.mvp.ui.fragment.HomeFragment.TYPE;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer)
    DrawerLayout mdrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.main_frame)
    FrameLayout mMainFrame;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    private TextView mTvName;
    private TextView mTvphone;
    private TextView mtvdanyuan;
    private TextView tv_fangjianhao;
    private TextView tv_mianji;
    protected CommonPreferences mPreferences;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    public void selectDrawerItem(MenuItem menuItem) {

        Intent intent = new Intent();
        switch (menuItem.getItemId()) {
            case R.id.item_order:

                intent.putExtra(RecycleActivity.TITLE, "订单记录查询");
                intent.putExtra(TYPE, "ORDER_HISTORY");
                intent.setClass(this, RecycleActivity.class);
                startActivity(intent);

                break;
            case R.id.item_xiaoxi:
                intent.setClass(this, MessageListActivity.class);
                startActivity(intent);
                break;
            case R.id.item_check_update:
                ToastUtil.showMessage("当前已经是最新版本！");

                break;
            case R.id.item_suggest:
                intent.setClass(this, SuggestActivity.class);
                startActivity(intent);
                break;
            case R.id.item_us:
                intent.setClass(this, MapActivity.class);
                intent.putExtra("title", "关于我们");
                startActivity(intent);
                break;
            case R.id.item_login_out:
                logOut();
                break;
        }

        // 改变状态
//        menuItem.setChecked(true);

        // 关闭抽屉
        mdrawer.closeDrawers();
    }

    public void logOut() {
        mPreferences.cleanCache(Constants.VERSION_PAIR[0]);
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mdrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mdrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        View headerView = mNavigationView.getHeaderView(0);
        mTvName = headerView.findViewById(R.id.tv_name);
        mTvphone = headerView.findViewById(R.id.tv_phone);
        mtvdanyuan = headerView.findViewById(R.id.tv_danyuan);
        tv_fangjianhao = headerView.findViewById(R.id.tv_fangjianhao);
        tv_mianji = headerView.findViewById(R.id.tv_mianji);

        HomeFragment homeFragment = HomeFragment.newInstance();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.main_frame, homeFragment).commitAllowingStateLoss();
        mPreferences = new CommonPreferences(MyApplication.APP);
        EntitiyUser.UserInfo user = mPreferences.getModel(EntitiyUser.UserInfo.class);
        if (user != null) {
            mTvName.setText(user.getOwnname());
            mTvphone.setText(user.getMobile());
            mtvdanyuan.setText(user.getBuildName());
            tv_fangjianhao.setText(user.getHouseName());
            tv_mianji.setText(user.getCoverArea());
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mdrawer.isDrawerOpen(GravityCompat.START)) {
                mdrawer.closeDrawer(GravityCompat.START);
            } else {
                exitBy2Click();
            }

        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {

        if (!isExit) {
            isExit = true;
            ToastUtil.showMessage(getString(R.string.please_press_to_exit));
            Timer tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            mPresenter.exit();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectDrawerItem(item);
        return true;
    }
}
