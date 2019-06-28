package hzyj.come.zhangshangpingtai.copy.commentrecycle;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.copy.BaseActivity;
import hzyj.come.zhangshangpingtai.entity.EntityOrderInfo;

public class DetailActivity extends BaseActivity {
    public static final String TITLE = "TITLE";
    public static final String TYPE = "TYPE";
    public static final String ORDER_DETAIL = "ORDER_DETAIL";
    public static final String PROJECT_DETAIL = "PROJECT_DETAIL";
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.dragRecyclerView)
    RecyclerView mDragRecyclerView;
    String URL;
    OrderDetailAdapter adapter;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setNavigationOnClickListener(v -> finish());
        String title = getIntent().getStringExtra(TITLE);
        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("正在加载");
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        if (!TextUtils.isEmpty(title)) {
            mToolbarTitle.setText(title + "缴费详情");
        }

        HashMap<String, String> params = new HashMap<>();
        String code = getIntent().getStringExtra("code");
        String id = getIntent().getStringExtra("id");
        if (!TextUtils.isEmpty(id)) {
            params.put("chargeManageId", id);
        }
        if (!TextUtils.isEmpty(code)) {
            params.put("code", code);
        }
        String type = getIntent().getStringExtra(TYPE);
        switch (type) {
            case ORDER_DETAIL:
                URL = NetWorkConstant.selectdetails;
                break;
            case PROJECT_DETAIL:
                URL = NetWorkConstant.gainProject;
                break;
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mDragRecyclerView.setLayoutManager(manager);
        adapter = new OrderDetailAdapter();
        if (adapter != null) {
            mDragRecyclerView.setAdapter(adapter);
        }
        mDialog.show();
        mGsonRequest.function(URL, params, EntityOrderInfo.class, new CallBack<EntityOrderInfo>() {
            @Override
            public void onResponse(EntityOrderInfo result) {
                mDialog.dismiss();
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    ArrayList<EntityOrderInfo.OrderInfo> orderInfos = result.getResult();
                    if (adapter != null && orderInfos != null && !orderInfos.isEmpty()) {
                        adapter.refresh(orderInfos);
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
                mDialog.dismiss();
                ToastUtil.showMessage(error);
            }
        });

    }
}
