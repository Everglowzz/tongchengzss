package hzyj.come.zhangshangpingtai.copy.repairs;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.MyApplication;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.CommonPreferences;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.copy.BaseActivity;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.entity.Entity;

public class RepairsActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_house)
    TextView mEtHouse;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.rb1)
    RadioButton mRb1;
    @BindView(R.id.rb2)
    RadioButton mRb2;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairs);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setNavigationOnClickListener(v -> finish());
        mToolbarTitle.setText("申请报修");
        CommonPreferences preferences = new CommonPreferences(MyApplication.APP);
        EntitiyUser.UserInfo user = preferences.getModel(EntitiyUser.UserInfo.class);
        if (user != null) {
            if (!TextUtils.isEmpty(user.getBuildName()) && !TextUtils.isEmpty(user.getHouseName())) {
                mEtHouse.setText(user.getBuildName() + "-" + user.getHouseName());
            }
            if (!TextUtils.isEmpty(user.getMobile())) {
                mEtPhone.setText(user.getMobile());
            }

        }
        mBtnSubmit.setOnClickListener(v -> subMitRepair());
    }

    private void subMitRepair() {

        HashMap<String, String> params = new HashMap<>();
        String phone = mEtPhone.getText().toString();
        String content = mEtContent.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showMessage("请填写联系电话");
            return;
        }
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showMessage("请填写报修内容");
            return;
        }
        params.put("phone", phone);
        params.put("content", content);
        params.put("property", mRadioGroup.getCheckedRadioButtonId() == R.id.rb1 ? "0" : "1");

//        ToastUtil.showMessage("提交成功");
//        finish();
        mGsonRequest.function(NetWorkConstant.repairApplication, params, Entity.class, new CallBack<Entity>() {
            @Override
            public void onResponse(Entity result) {
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    ToastUtil.showMessage("提交成功");
                    finish();
                } else {
                    ToastUtil.showMessage(result.getMessage());
                }
            }

            @Override
            public void onFailure(String error) {
                ToastUtil.showMessage(error);
            }
        });
    }
}
