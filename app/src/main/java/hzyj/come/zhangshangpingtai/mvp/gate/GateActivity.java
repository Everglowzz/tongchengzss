package hzyj.come.zhangshangpingtai.mvp.gate;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;
import com.jess.arms.utils.ArmsUtils;

import java.util.Arrays;

import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.copy.BaseActivity;
import hzyj.come.zhangshangpingtai.mvp.login.LoginActivity;
import hzyj.come.zhangshangpingtai.mvp.ui.activity.MainActivity;
import hzyj.come.zhangshangpingtai.mvp.welcome.WelcomeActivity;

public class GateActivity extends BaseActivity implements GateContract.view, OnPermissionCallback {

    private GatePresenter mPresenter;
    private static final String PERMISSION = "permissionHelper";
    private PermissionHelper permissionHelper;
    private boolean isSingle;
    private AlertDialog builder;
    private String[] neededPermission;

    private final static String SINGLE_PERMISSION = Manifest.permission.READ_PHONE_STATE;


    private final static String[] MULTI_PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ConstraintLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);
        mRootView = findViewById(R.id.rootView);
        initPermission();
       
        mPresenter = new GatePresenter(this);
        
    }
    private void initPermission() {
        permissionHelper = PermissionHelper.getInstance(this);
        permissionHelper
                .setForceAccepting(false) // default is false. its here so you know that it exists.
                .request(isSingle ? SINGLE_PERMISSION : MULTI_PERMISSIONS);
    }

    @Override
    public void skipMain() {
        ArmsUtils.startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void skipLogin() {
        ArmsUtils.startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void skipWelcome(String url) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("url",url);
        ArmsUtils.startActivity(intent);
        finish();
    }

    @Override
    public void killSelf() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        permissionHelper.onActivityForResult(requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        Log.i(PERMISSION, "Permission(s) " + Arrays.toString(permissionName) + " Granted");
        mRootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.judge();
            }
        }, 1000);
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
        Log.i(PERMISSION, "Permission(s) " + Arrays.toString(permissionName) + " Declined");
        finish();
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
        Log.i(PERMISSION, "Permission( " + permissionsName + " ) preGranted");
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName) {
        Log.i(PERMISSION, "Permission( " + permissionName + " ) needs Explanation");
        if (!isSingle) {
            neededPermission = PermissionHelper.declinedPermissions(this, MULTI_PERMISSIONS);
            StringBuilder builder = new StringBuilder(neededPermission.length);
            if (neededPermission.length > 0) {
                for (String permission : neededPermission) {
                    builder.append(permission).append("\n");
                }
            }
            AlertDialog alert = getAlertDialog(neededPermission, builder.toString());
            if (!alert.isShowing()) {
                alert.show();
            }
        } else {
            getAlertDialog(permissionName).show();
        }
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {
        Log.i(PERMISSION, "Permission " + permissionName + " can only be granted from settingsScreen");
        /** you can call  {@link PermissionHelper#openSettingsScreen(Context)} to open the settings screen */

    }

    @Override
    public void onNoPermissionNeeded() {
        Log.i("onNoPermissionNeeded", "Permission(s) not needed");
    }

    public AlertDialog getAlertDialog(final String[] permissions, final String permissionName) {
        if (builder == null) {
            builder = new AlertDialog.Builder(this)
                    .setTitle("Permission Needs Explanation")
                    .create();
        }
        builder.setButton(DialogInterface.BUTTON_POSITIVE, "Request", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                permissionHelper.requestAfterExplanation(permissions);
            }
        });
        builder.setMessage("Permissions need explanation (" + permissionName + ")");
        return builder;
    }

    public AlertDialog getAlertDialog(final String permission) {
        if (builder == null) {
            builder = new AlertDialog.Builder(this)
                    .setTitle("Permission Needs Explanation")
                    .create();
        }
        builder.setButton(DialogInterface.BUTTON_POSITIVE, "Request", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                permissionHelper.requestAfterExplanation(permission);
            }
        });
        builder.setMessage("Permission need explanation (" + permission + ")");
        return builder;
    }

}
