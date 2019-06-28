package hzyj.come.zhangshangpingtai.copy.message;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.copy.BaseActivity;
import hzyj.come.zhangshangpingtai.copy.CommonAdapter;
import hzyj.come.zhangshangpingtai.entity.EntityMessage;

public class MessageListActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.dragRecyclerView)
    RecyclerView mDragRecyclerView;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    CommonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setNavigationOnClickListener(v -> finish());
        mToolbarTitle.setText("通知列表");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mDragRecyclerView.setLayoutManager(manager);
        adapter = new MessageAdapter();
        mDragRecyclerView.setAdapter(adapter);
//        mTvEmpty.setVisibility(View.VISIBLE);
        mGsonRequest.function(NetWorkConstant.selectMessageInformAll, null, EntityMessage.class, new CallBack<EntityMessage>() {
            @Override
            public void onResponse(EntityMessage result) {
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    ArrayList<EntityMessage.MessageInfo> messageInfos = result.getResult();
                    if (adapter != null && messageInfos != null && !messageInfos.isEmpty()) {
                        adapter.refresh(messageInfos);
                        mTvEmpty.setVisibility(View.GONE);
                    } else {
                        mTvEmpty.setVisibility(View.VISIBLE);
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
}
