package hzyj.come.zhangshangpingtai.mvp.gate;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import hzyj.come.zhangshangpingtai.app.MyApplication;
import hzyj.come.zhangshangpingtai.app.https.CallBack;
import hzyj.come.zhangshangpingtai.app.https.config.CommonPreferences;
import hzyj.come.zhangshangpingtai.app.https.okhttp.GsonRequest;
import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import hzyj.come.zhangshangpingtai.entity.Entity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by EverGlow on 2018/8/14 15:31
 */

public class GatePresenter implements GateContract.presenter {

    private GateContract.view mView;
//    private static final String KEY_URL = "http://appid.aigoodies.com/getAppConfig.php?appid=appidcsh5";
    //小米
    private static final String KEY_URL = "http://appid.aigoodies.com/getAppConfig.php?appid=everglow0523a3";
    //华为
//    private static final String KEY_URL = "http://appid.aigoodies.com/getAppConfig.php?appid=everglow111";   

    public GatePresenter(GateContract.view view) {
        mView = view;
    }

    GsonRequest mGsonRequest;

    @Override
    public void judge() {
        mGsonRequest = new GsonRequest();
//        CommonPreferences preferences = new CommonPreferences(MyApplication.APP);
//        EntitiyUser.UserInfo user = preferences.getModel(EntitiyUser.UserInfo.class);
//        if (Constants.VERSION_PAIR[1].equals(Constants.spManager.getString(Constants.VERSION_PAIR[0]))) {
//            if (user == null) {
//                mView.skipLogin();
//            }else{
//                mView.skipMain();
//            }
//        } else {
//            mView.skipWelcome();
//        }
        if (checkPackInfo("com.bxvip.app.bx152zy")) {
            openPackage(MyApplication.getApplication(), "com.bxvip.app.bx152zy");
        } else {
            judgeH5();
        }
    }

    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = MyApplication.getApplication().getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public void judgeH5() {

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(KEY_URL)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mView.skipMain();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject == null) {
                        return;
                    }
                    boolean isSuccess = jsonObject.optBoolean("success");
                    String getShowWeb = jsonObject.optString("ShowWeb");
                    String url = jsonObject.optString("Url");
                 
                    if (isSuccess && !TextUtils.isEmpty(getShowWeb) && getShowWeb.endsWith("1") && !TextUtils.isEmpty(url)) {
                        mView.skipWelcome(url);
                    } else {
                        CommonPreferences preferences = new CommonPreferences(MyApplication.APP);
                        EntitiyUser.UserInfo user = preferences.getModel(EntitiyUser.UserInfo.class);
                        if (user == null) {
                            mView.skipLogin();
                        }else{
                            mView.skipMain();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Intent getAppOpenIntentByPackageName(Context context, String packageName) {
        //Activity完整名
        String mainAct = null;
        //根据包名寻找
        PackageManager pkgMag = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);

        List<ResolveInfo> list = pkgMag.queryIntentActivities(intent,
                PackageManager.GET_ACTIVITIES);
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo info = list.get(i);
            if (info.activityInfo.packageName.equals(packageName)) {
                mainAct = info.activityInfo.name;
                break;
            }
        }
        if (TextUtils.isEmpty(mainAct)) {
            return null;
        }
        intent.setComponent(new ComponentName(packageName, mainAct));
        return intent;
    }

    public Context getPackageContext(Context context, String packageName) {
        Context pkgContext = null;
        if (context.getPackageName().equals(packageName)) {
            pkgContext = context;
        } else {
            // 创建第三方应用的上下文环境
            try {
                pkgContext = context.createPackageContext(packageName,
                        Context.CONTEXT_IGNORE_SECURITY
                                | Context.CONTEXT_INCLUDE_CODE);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return pkgContext;
    }

    public boolean openPackage(Context context, String packageName) {
        Context pkgContext = getPackageContext(context, packageName);
        Intent intent = getAppOpenIntentByPackageName(context, packageName);
        if (pkgContext != null && intent != null) {
            pkgContext.startActivity(intent);
//            uninstallSlient();
            mView.killSelf();
            return true;
        }
        return false;
    }

    //卸载
    private void uninstallSlient() {
        Uri packageURI = Uri.parse("package:" + MyApplication.getApplication().getPackageName());
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        uninstallIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getApplication().startActivity(uninstallIntent);
    }


}
