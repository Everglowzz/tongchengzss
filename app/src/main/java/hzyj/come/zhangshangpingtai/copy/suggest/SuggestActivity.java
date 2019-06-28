package hzyj.come.zhangshangpingtai.copy.suggest;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

/**
 * Created by EverGlow on 2019/1/9 16:48
 * 投诉建议
 */

public class SuggestActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
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
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.rb_form1)
    RadioButton mRbForm1;
    @BindView(R.id.rb_form2)
    RadioButton mRbForm2;
    @BindView(R.id.radio_form)
    RadioGroup mRadioForm;
    @BindView(R.id.et_complaint_person)
    EditText mEtComplaintPerson;
    @BindView(R.id.complaint_rb1)
    RadioButton mComplaintRb1;
    @BindView(R.id.complaint_rb2)
    RadioButton mComplaintRb2;
    @BindView(R.id.radio_complaint_type)
    RadioGroup mRadioComplaintType;
    @BindView(R.id.ll_complaint)
    LinearLayout mLlComplaint;
    @BindView(R.id.ll_isUrgency)
    LinearLayout mLlIsUrgency;
    @BindView(R.id.ll_type)
    LinearLayout mLlType;
    @BindView(R.id.et_complaint_department)
    EditText mEtComplaintDepartment;
    @BindView(R.id.ll_department)
    LinearLayout mLlDepartment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setNavigationOnClickListener(v -> finish());
        mToolbarTitle.setText("投诉建议");

        CommonPreferences preferences = new CommonPreferences(MyApplication.APP);
        EntitiyUser.UserInfo user = preferences.getModel(EntitiyUser.UserInfo.class);
        if (user != null) {
            if (!TextUtils.isEmpty(user.getMobile())) {
                mEtPhone.setText(user.getMobile());
            }
        }

        mRadioForm.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_form1) {
                setViewVisibility(true);
            } else {
                setViewVisibility(false);
            }
        });

        mBtnSubmit.setOnClickListener(v -> {
            finish();
//            ToastUtil.showMessage("提交成功");
            subMitSuggest();
        });

    }


    private void subMitSuggest() {

        HashMap<String, String> params = new HashMap<>();
        String phone = mEtPhone.getText().toString();
        String content = mEtContent.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showMessage("请填写联系电话");
            return;
        }
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showMessage("请填写投诉内容");
            return;
        }
        if (mRadioForm.getCheckedRadioButtonId() == R.id.rb_form1) {
            String person = mEtComplaintPerson.getText().toString();
            String mEtComplaintDepartment = mEtComplaintPerson.getText().toString();
            params.put("comperson", TextUtils.isEmpty(person) ? "" : person);
            params.put("comdept", TextUtils.isEmpty(mEtComplaintDepartment) ? "" : mEtComplaintDepartment);
            params.put("isUrgency", mRadioGroup.getCheckedRadioButtonId() == R.id.rb1 ? "1" : "0");
            params.put("comtype", mRadioComplaintType.getCheckedRadioButtonId() == R.id.complaint_rb1 ? "服务质量" : "服务态度");
            params.put("title", "投诉");

        } else {
            params.put("title", "建议");
        }
        params.put("phone", phone);
        params.put("content", content);

        mGsonRequest.function(NetWorkConstant.ComplaintProposalApplication, params, Entity.class, new CallBack<Entity>() {
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

    public void setViewVisibility(boolean isVisibility) {
        mLlComplaint.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        mLlIsUrgency.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        mLlType.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        mLlDepartment.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
    }

}
