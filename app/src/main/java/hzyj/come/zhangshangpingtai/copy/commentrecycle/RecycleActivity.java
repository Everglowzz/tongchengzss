package hzyj.come.zhangshangpingtai.copy.commentrecycle;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
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
import hzyj.come.zhangshangpingtai.copy.CommonAdapter;
import hzyj.come.zhangshangpingtai.entity.EntityOrderInfo;
import hzyj.come.zhangshangpingtai.mvp.ui.fragment.HomeFragment;

import static hzyj.come.zhangshangpingtai.mvp.ui.fragment.HomeFragment.ORDER_TYPE;
import static hzyj.come.zhangshangpingtai.mvp.ui.fragment.HomeFragment.PAYMENT;
import static hzyj.come.zhangshangpingtai.mvp.ui.fragment.HomeFragment.PROJECT_QUERY;

public class RecycleActivity extends BaseActivity {

    public static final String TITLE = "title";

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_empty)
    TextView mTvempty;
    @BindView(R.id.head_item)
    LinearLayout mHeadItem;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.dragRecyclerView)
    RecyclerView mDragRecyclerView;
    CommonAdapter adapter;
    String URL;
    private String mType;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        ButterKnife.bind(this);
        
        initView();
    }

    private void initView() {
        mToolbar.setNavigationOnClickListener(v -> finish());

        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("正在加载");
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        String title = getIntent().getStringExtra(TITLE);
        if (!TextUtils.isEmpty(title)) {
            mToolbarTitle.setText(title);
        }
        mType = getIntent().getStringExtra(HomeFragment.TYPE);
        HashMap<String, String> params = new HashMap<>();

        switch (mType) {
            case ORDER_TYPE:
                URL = NetWorkConstant.selectchasyn;
                LinearLayoutManager manager = new LinearLayoutManager(this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                mDragRecyclerView.setLayoutManager(manager);
                adapter = new OrderAdapter();
                break;
            case PROJECT_QUERY:
                URL = NetWorkConstant.project_query;
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                mDragRecyclerView.setLayoutManager(gridLayoutManager);
                adapter = new ProjectAdapter();
                break;
            case PAYMENT:
                URL = NetWorkConstant.payment;
                break;
        }
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

                        mHeadItem.setVisibility(mType.equals(ORDER_TYPE) ? View.VISIBLE : View.GONE);
                        adapter.refresh(orderInfos);
                        mTvempty.setVisibility(View.GONE);
                    } else {
                        mTvempty.setVisibility(View.VISIBLE);
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
