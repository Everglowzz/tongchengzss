package hzyj.come.zhangshangpingtai.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.everglow.mimixiao.DangAnActivity;
import com.everglow.mimixiao.JieMengActivity;
import com.everglow.mimixiao.JieQi;
import com.everglow.mimixiao.JieqiActivity;
import com.everglow.mimixiao.ShengXiaoActivity;
import com.everglow.mimixiao.XingZuoActivity;
import com.everglow.mimixiao.bean.XingZuo;
import com.example.wannianli.WannianliActivity;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzyj.come.zhangshangpingtai.BuildConfig;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.app.utils.Tools;
import hzyj.come.zhangshangpingtai.base.BaseFragment;
import hzyj.come.zhangshangpingtai.copy.CommonAdapter;
import hzyj.come.zhangshangpingtai.copy.commentrecycle.ProjectAdapter;
import hzyj.come.zhangshangpingtai.copy.commentrecycle.RecycleActivity;
import hzyj.come.zhangshangpingtai.copy.dialog.UpdateDialog;
import hzyj.come.zhangshangpingtai.custom.MarqueeTextView;
import hzyj.come.zhangshangpingtai.entity.EntityInform;
import hzyj.come.zhangshangpingtai.entity.EntityOrderInfo;
import hzyj.come.zhangshangpingtai.entity.EntityVersion;
import hzyj.come.zhangshangpingtai.mvp.ui.activity.OpenDoor;
import hzyj.come.zhangshangpingtai.mvp.ui.widget.GlideImageLoader;
import me.shihao.library.XRadioGroup;

/**
 * Created by EverGlow on 2018/4/11 11:52
 */
public class HomeFragment extends BaseFragment {

    public static final String TYPE = "TYPE";
    public static final String ORDER_TYPE = "ORDER_HISTORY";
    public static final String PROJECT_QUERY = "PROJECT_QUERY";
    public static final String PAYMENT = "PAYMENT";
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_inform)
    LinearLayout mLlInfo;
    @BindView(R.id.tv_roll_text)
    MarqueeTextView mTvRollText;

    @BindView(R.id.tv_jiri)
    TextView mTvjiri;
    @BindView(R.id.tv_jieqi)
    TextView mTvjieqi;
    @BindView(R.id.tv_chepai)
    TextView mTvchepai;
    @BindView(R.id.tv_shouji)
    TextView mTvshouji;
    @BindView(R.id.tv_shengxiao)
    TextView tv_shengxiao;
    @BindView(R.id.tv_xingzuo)
    TextView tv_xingzuo;
    @BindView(R.id.tv_jiemeng)
    TextView tv_jiemeng;

    Unbinder unbinder;
    private UpdateDialog mUpdateDialog;
    private ArrayList<String> images;

    @BindView(R.id.head_item)
    LinearLayout mHeadItem;
    CommonAdapter adapter;
    String URL;

    String apkPath;
    private EntityVersion.VersionInfo mInfo;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }


    public void startWeb(String title, String url) {
        Intent intent = new Intent(getActivity(), DangAnActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        getActivity().startActivity(intent);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mGsonRequest.function(NetWorkConstant.ArrearageInform, null, EntityInform.class, new CallBack<EntityInform>() {
            @Override
            public void onResponse(EntityInform result) {
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    mLlInfo.setVisibility(View.VISIBLE);
                    mTvRollText.setText(result.getResult());
                } else {
                    mLlInfo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String error) {
                mLlInfo.setVisibility(View.GONE);
            }
        });


        initBanner();
//        checkUpdate();

        tv_shengxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ShengXiaoActivity.class));
            }
        });
        tv_jiemeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), JieMengActivity.class));
            }
        });
        tv_xingzuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), XingZuoActivity.class));
            }
        });
        mTvjiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WannianliActivity.class));
            }
        });
        mTvjieqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), JieqiActivity.class));
            }
        });
        mTvchepai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWeb("车牌号凶吉测试", "http://aliyun.zhanxingfang.com/zxf/web_free/car.php");
            }
        });
        mTvshouji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWeb("手机号凶吉测试", "http://aliyun.zhanxingfang.com/zxf/web_free/mobile.php");
            }
        });
    }


    private void checkUpdate() {
        URL = NetWorkConstant.project_query;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        adapter = new ProjectAdapter();
        HashMap<String, String> params = new HashMap<>();

        mGsonRequest.function(URL, params, EntityOrderInfo.class, new CallBack<EntityOrderInfo>() {
            @Override
            public void onResponse(EntityOrderInfo result) {
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    ArrayList<EntityOrderInfo.OrderInfo> orderInfos = result.getResult();
                    if (adapter != null && orderInfos != null && !orderInfos.isEmpty()) {
//                        mHeadItem.setVisibility(View.VISIBLE);
                        adapter.refresh(orderInfos);
//                        mTvempty.setVisibility(View.GONE);
                    } else {
//                        mTvempty.setVisibility(View.VISIBLE);
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

    private void initBanner() {
        images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554206925036&di=bda244a98d2a7d8e1b3e6aaec201a514&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181025%2F1396293d5dba4d3a9bcfcb6324e612b2.jpeg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546598508853&di=384d59b788632384f1c0fd8c614d831b&imgtype=0&src=http%3A%2F%2Fwww.zgwygl.org%2FUploadFiles%2F2014-07%2Fzhoulei%2F2014071017284583578.jpg");
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=162480343,2496457795&fm=26&gp=0.jpg");
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
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

    @OnClick({R.id.rl_project_query, R.id.rl_jiaofei})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_order_history:
                intent.putExtra(RecycleActivity.TITLE, "订单记录查询");
                intent.putExtra(TYPE, ORDER_TYPE);
                intent.setClass(getActivity(), RecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_project_query:
                intent.putExtra(RecycleActivity.TITLE, "缴费项目查询");
                intent.putExtra(TYPE, PROJECT_QUERY);
                intent.setClass(getActivity(), RecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_jiaofei:
                intent.setClass(getActivity(), OpenDoor.class);
                startActivity(intent);
                break;
        }

    }


}
