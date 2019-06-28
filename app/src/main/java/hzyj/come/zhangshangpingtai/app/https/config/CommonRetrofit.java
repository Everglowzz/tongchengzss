package hzyj.come.zhangshangpingtai.app.https.config;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;


import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import hzyj.come.zhangshangpingtai.app.MyApplication;
import hzyj.come.zhangshangpingtai.app.https.okhttp.HttpRequest;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Youga on 2016/3/22.
 */
public class CommonRetrofit {

    private static final String HTTP_CACHE_FILENAME = "HttpCache";
    private final CommonService commonService;

    @Inject
    public CommonRetrofit() {
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpRequest.HOST_VALUE)
                .client(initOkHttpClient(MyApplication.APP))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        commonService = retrofit.create(CommonService.class);
    }

    public CommonService createService() {
        return commonService;
    }


    public static OkHttpClient initOkHttpClient(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir().getAbsolutePath(),
                HTTP_CACHE_FILENAME);
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        return new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(new LogInterceptor(context))
                .cache(cache)
                .build();
    }

    public static OkHttpClient extraOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static Gson getGson() {
        return new Gson();
    }

    public static Handler getDelivery() {
        return new Handler(Looper.getMainLooper());
    }

}
