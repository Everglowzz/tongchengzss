package hzyj.come.zhangshangpingtai.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import hzyj.come.zhangshangpingtai.BuildConfig;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.NetWorkConstant;
import hzyj.come.zhangshangpingtai.app.utils.ToastUtil;
import hzyj.come.zhangshangpingtai.base.BaseFragment;
import hzyj.come.zhangshangpingtai.copy.AcUserInfo;
import hzyj.come.zhangshangpingtai.copy.CertificationActivity;
import hzyj.come.zhangshangpingtai.copy.MapActivity;
import hzyj.come.zhangshangpingtai.copy.bankcard.CardManngeActivity;
import hzyj.come.zhangshangpingtai.copy.commentrecycle.RecycleActivity;
import hzyj.come.zhangshangpingtai.copy.dialog.UpdateDialog;
import hzyj.come.zhangshangpingtai.copy.message.MessageListActivity;
import hzyj.come.zhangshangpingtai.copy.suggest.SuggestActivity;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.entity.EntityVersion;
import hzyj.come.zhangshangpingtai.global.Constants;
import hzyj.come.zhangshangpingtai.mvp.login.LoginActivity;
import hzyj.come.zhangshangpingtai.mvp.ui.widget.GroupViewItem;

import static hzyj.come.zhangshangpingtai.mvp.ui.fragment.HomeFragment.TYPE;

/**
 * Created by EverGlow on 2018/4/11 11:52
 */
public class MineFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_danyuan)
    TextView mTvdanyuan;
    @BindView(R.id.tv_fangjianhao)
    TextView mTvfangjianhao;
    @BindView(R.id.tv_mianji)
    TextView mTvmianji;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;
    @BindView(R.id.item_card)
    GroupViewItem mItemCard;
    @BindView(R.id.item_truth)
    GroupViewItem mItemTruth;
    @BindView(R.id.item_address)
    GroupViewItem mItemAddress;
    @BindView(R.id.item_setting)
    GroupViewItem mItemSetting;
    @BindView(R.id.item_xiaoxi)
    GroupViewItem mItemOrder;
    @BindView(R.id.item_suggest)
    GroupViewItem mItemShopping;
    @BindView(R.id.item_check_update)
    GroupViewItem mItemCheckUpdate;
    @BindView(R.id.item_us)
    GroupViewItem mItemUs;
    @BindView(R.id.btn_logout)
    Button mBtnLogout;
    private EntityVersion.VersionInfo mInfo;
    private UpdateDialog mUpdateDialog;
    String apkPath;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        EntitiyUser.UserInfo user = mPreferences.getModel(EntitiyUser.UserInfo.class);
        if (user != null) {
            mTvName.setText(user.getOwnname());
            mTvPhone.setText(user.getMobile());
            mTvdanyuan.setText(user.getBuildName());
            mTvfangjianhao.setText(user.getHouseName());
            mTvmianji.setText(user.getCoverArea());
        }

        mItemCheckUpdate.setRightText("v" + BuildConfig.VERSION_NAME);
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


    public void logOut() {
        mBtnLogout.setEnabled(false);
        mPreferences.cleanCache(Constants.VERSION_PAIR[0]);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

    }

    @OnClick({R.id.profile_image, R.id.item_card, R.id.item_truth, R.id.item_xiaoxi, R.id.item_suggest, R.id.item_address, R.id.item_setting, R.id.item_check_update, R.id.item_us, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                startActivity(new Intent(getActivity(), AcUserInfo.class));
                break;
            case R.id.item_card:
                startActivity(new Intent(getActivity(), CardManngeActivity.class));
                break;
            case R.id.item_truth:
                startActivity(new Intent(getActivity(), CertificationActivity.class));
                break;
            //消息通知
            case R.id.item_xiaoxi:
                startActivity(new Intent(getActivity(), MessageListActivity.class));
                break;
            //门禁人像设置
            case R.id.item_setting:
//                startActivity(new Intent(getActivity(), SettingActivity.class));
                ToastUtil.showMessage("正在开发中");
                break;
            //投诉建议
            case R.id.item_suggest:
                startActivity(new Intent(getActivity(), SuggestActivity.class));
                break;
            //检测更新
            case R.id.item_check_update:
//                checkUpdate();
                ToastUtil.showMessage("当前已经是最新版本！");
                break;
            //关于我们
            case R.id.item_us:
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("title", "关于我们");
                startActivity(intent);
                break;
            case R.id.btn_logout:
                logOut();
                break;
            case R.id.item_address:
                Intent intent1 =  new Intent();
                intent1.putExtra(RecycleActivity.TITLE, "订单记录查询");
                intent1.putExtra(TYPE, "ORDER_HISTORY");
                intent1.setClass(getActivity(), RecycleActivity.class);
                startActivity(intent1);
                break;
        }
    }


    private void checkUpdate() {
        mItemCheckUpdate.setEnabled(false);
        mGsonRequest.function(NetWorkConstant.checkUpdates, null, EntityVersion.class, new CallBack<EntityVersion>() {
            @Override
            public void onResponse(EntityVersion result) {
                if (result.getCode() == NetWorkConstant.REQUEST_SUCCESS) {
                    mInfo = result.getResult();
                    if (mInfo != null && BuildConfig.VERSION_CODE != mInfo.getVersionsCode()) {
                        initUpDate(NetWorkConstant.HOST_VALUE + mInfo.getPath(), mInfo.getVersionsExplain(), mInfo.getVersionsCode(), mInfo.getIfUpdate());
                    } else {
                        ToastUtil.showMessage("当前已经是最新版本！");
                    }
                }
                mItemCheckUpdate.setEnabled(true);
            }

            @Override
            public void onFailure(String error) {
                ToastUtil.showMessage("检测版本失败！");
                mItemCheckUpdate.setEnabled(true);
            }
        });

    }

    /**
     * @param downloadUrl   下载url
     * @param desc          更新描述
     * @param latestVersion 最新版本号
     * @param ifUpdate      是否强制更新
     */
    private void initUpDate(final String downloadUrl, String desc, final int latestVersion, int ifUpdate) {
//        apkPath = Tools.getExternalStoragePublicDirectory() + File.separator + getString(R.string.apk_name);
//        mUpdateDialog = new UpdateDialog(getContext());
//        if (ifUpdate == 1) {
//            mUpdateDialog.getCancelButton().setOnClickListener(v -> getActivity().finish());
//        }
//        mUpdateDialog.setUpdateDialogCallBack(() -> {
//            DownloadManagerUtil.getInstance(getContext()).downloadApk(apkPath, downloadUrl, latestVersion);
//        });
//        mUpdateDialog.showDialog(desc);
    }

}
