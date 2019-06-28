package hzyj.come.zhangshangpingtai.global;

import android.os.Environment;

import hzyj.come.zhangshangpingtai.app.MyApplication;
import hzyj.come.zhangshangpingtai.utlis.SpManager;

/**
 * Created by EverGlow on 2018/3/14 16:52
 */

public    class Constants {
    public static SpManager spManager = new SpManager(MyApplication.getApplication());
    public static final String[] VERSION_PAIR = {"VERSION", "1.0.0"};
    public static  int SUCCESSCODE = 0;
    public static  int ERRORCODE = 200;
    public static final String DATA = "DATA";
    public static final String TITLE = "TITLE";
    public static final String ORDER_ID = "order_id";
    public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String DEFAULT_CACHE_DIR = SDCARD_DIR + "/PLDroidPlayer";
}
