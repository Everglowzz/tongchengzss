package hzyj.come.zhangshangpingtai.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.base.BaseFragment;
import hzyj.come.zhangshangpingtai.copy.repairs.RepairsActivity;
import hzyj.come.zhangshangpingtai.copy.repairs.RepairsHistoryAdapter;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.entity.EntityRepairs;

/**
 * Created by EverGlow on 2018/4/11 11:52
 */
public class RepairFragment extends BaseFragment {
    private static final String TAG = "RepairFragment";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    Unbinder unbinder;
    private EntitiyUser.UserInfo mUser;
    private RepairsHistoryAdapter mAdapter;

    public static RepairFragment newInstance() {
        RepairFragment fragment = new RepairFragment();
        return fragment;
    }


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repair, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new RepairsHistoryAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mUser = mPreferences.getModel(EntitiyUser.UserInfo.class);
//        requestHistory();

    }

    private void requestHistory() {
        HashMap<String, String> params = new HashMap<>();
        if (mUser != null) {
            params.put("housenumber", mUser.getHouseName());
        }
        mGsonRequest.function(NetWorkConstant.repairsList, params, EntityRepairs.class, new CallBack<EntityRepairs>() {
            @Override
            public void onResponse(EntityRepairs result) {
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    ArrayList<EntityRepairs.RepairsInfo> orderInfos = result.getResult();
                    if (mAdapter != null && orderInfos != null && !orderInfos.isEmpty()) {
                        mAdapter.refresh(orderInfos);
                        mTvEmpty.setVisibility(View.GONE);
                    } else {
                        mTvEmpty.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(String error) {
                ToastUtil.showMessage(error);
                mTvEmpty.setVisibility(View.VISIBLE);
            }
        });
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

    @OnClick(R.id.btn)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), RepairsActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        Log.d(TAG, "onFragmentVisibleChange +" + "isVisible =" + isVisible);
        requestHistory();

    }
}
