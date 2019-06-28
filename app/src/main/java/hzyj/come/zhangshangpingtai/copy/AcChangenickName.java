package hzyj.come.zhangshangpingtai.copy;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.entity.EntityBase;
import hzyj.come.zhangshangpingtai.global.Constants;

public class AcChangenickName extends BaseActivity {


    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nick_name)
    EditText mNickName;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_changenick_name);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mToolbarTitle.setText("修改昵称");
        mNickName.setText("");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickNme = mNickName.getText().toString().trim();
                if (TextUtils.isEmpty(nickNme)) {
                    ToastUtil.showMessage("输入不能为空！");
                } else {
                    changeNickname(nickNme);
                }
            }
        });
    }

    private void changeNickname(String nickname) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("appNickname", nickname);
        mGsonRequest.function(NetWorkConstant.app_edit_info, params, EntityBase.class, new CallBack<EntityBase>() {
            @Override
            public void onResponse(EntityBase result) {
                if (result.getCode() == Constants.SUCCESSCODE) {
                    EntitiyUser users =  result.getAppUsers();
                    CommonPreferences preferences = new CommonPreferences(MyApplication.APP);
                    preferences.setModel(users);
                    finish();
                }
                ToastUtil.showMessage(result.getMsg());
            }

            @Override
            public void onFailure(String error) {
                ToastUtil.showMessage(error);
            }
        });

    }
}
