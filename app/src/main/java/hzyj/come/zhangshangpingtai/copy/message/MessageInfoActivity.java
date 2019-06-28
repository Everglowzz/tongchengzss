package hzyj.come.zhangshangpingtai.copy.message;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.copy.BaseActivity;
import hzyj.come.zhangshangpingtai.entity.EntityMessage;
import hzyj.come.zhangshangpingtai.entity.EntityMessageInfo;

public class MessageInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_publisher)
    TextView mTvPublisher;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setNavigationOnClickListener(v -> finish());
        mToolbarTitle.setText("通知详情");
        HashMap<String, String> params = new HashMap<>();
        String id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)) return;
        params.put("id", id);
        mGsonRequest.function(NetWorkConstant.selectMessageInformOne, params, EntityMessageInfo.class, new CallBack<EntityMessageInfo>() {
            @Override
            public void onResponse(EntityMessageInfo result) {
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    EntityMessage.MessageInfo messageInfo = result.getResult();
                    if (messageInfo != null) {
                        initData(messageInfo);
                    }

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

    private void initData(EntityMessage.MessageInfo messageInfo) {
        
        mTvPublisher.setText(messageInfo.getPublisher());
        mTvContent.setText("\t\t"+messageInfo.getContent());
        mTvType.setText(messageInfo.getType());
        mTvTitle.setText(messageInfo.getTitle());
        mTvDate.setText(messageInfo.getDate());

    }
}
