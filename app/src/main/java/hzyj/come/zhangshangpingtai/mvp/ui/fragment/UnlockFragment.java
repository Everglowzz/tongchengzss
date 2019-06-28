package hzyj.come.zhangshangpingtai.mvp.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ScrollView;

import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.base.BaseFragment;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import me.shihao.library.XRadioGroup;

/**
 * Created by EverGlow on 2018/4/11 11:52
 */
public class UnlockFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.rb1)
    RadioButton mRb1;
    @BindView(R.id.rb2)
    RadioButton mRb2;
    @BindView(R.id.rb3)
    RadioButton mRb3;
    @BindView(R.id.rb4)
    RadioButton mRb4;
    @BindView(R.id.rb5)
    RadioButton mRb5;
    @BindView(R.id.radio_group)
    XRadioGroup mRadioGroup;
    @BindView(R.id.btn_ok)
    Button mBtnOk;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;

    public static UnlockFragment newInstance() {
        UnlockFragment fragment = new UnlockFragment();
        return fragment;
    }

    private int mDoorType = -1;
    private CountDownTimer mDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (mBtnOk != null) {
                
            mBtnOk.setText("请等待" + millisUntilFinished / 1000 + "秒再试");
            }
        }

        @Override
        public void onFinish() {
            if (mBtnOk != null) {
                mBtnOk.setText("请求开启");
                mBtnOk.setEnabled(true);
            }
           
        }
    };

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.unlock, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        String danyuanname = "单元门\n";

        EntitiyUser.UserInfo user = mPreferences.getModel(EntitiyUser.UserInfo.class);
        if (user != null) {
            danyuanname = danyuanname + user.getBuildName();
        }
        mRb5.setText(danyuanname);
        mRadioGroup.setOnCheckedChangeListener((xRadioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.rb1:
                    mDoorType = 1;
                    break;
                case R.id.rb2:
                    mDoorType = 2;
                    break;
                case R.id.rb3:
                    mDoorType = 3;
                    break;
                case R.id.rb4:
                    mDoorType = 4;
                    break;
                case R.id.rb5:
                    mDoorType = 0;
                    break;
            }
            mBtnOk.setEnabled(true);
        });
        mBtnOk.setOnClickListener(v -> openDoor());
    }

    private void openDoor() {

        ToastUtil.showMessage("开门请求成功");
        mBtnOk.setEnabled(false);
        mDownTimer.start();
//        HashMap<String, String> params = new HashMap<>();
//        params.put("code", String.valueOf(mDoorType));
//        mGsonRequest.function(NetWorkConstant.openDoor, params, Entity.class, new CallBack<Entity>() {
//            @Override
//            public void onResponse(Entity result) {
//                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
//                    ToastUtil.showMessage("开门成功");
//                    mBtnOk.setEnabled(false);
//                    mDownTimer.start();
//                } else {
//                    ToastUtil.showMessage(result.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(String error) {
//                ToastUtil.showMessage(error);
//            }
//        });


    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
