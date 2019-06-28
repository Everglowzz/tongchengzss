package hzyj.come.zhangshangpingtai.mvp.ui.activity;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import me.shihao.library.XRadioGroup;

public class OpenDoor extends AppCompatActivity {
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
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private int mDoorType = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door);
        ButterKnife.bind(this);
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
        mToolbar.setNavigationOnClickListener(v -> finish());
            mToolbarTitle.setText("远程开启门禁");
    }

    private void openDoor() {
//        startActivity(new Intent(getActivity(), WannianliActivity.class));
        ToastUtil.showMessage("开门请求成功");
        mBtnOk.setEnabled(false);
        mDownTimer.start();
    }

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

}
