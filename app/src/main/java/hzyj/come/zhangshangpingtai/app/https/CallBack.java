package hzyj.come.zhangshangpingtai.app.https;


/**
 * Created by Youga on 2015/8/25.
 */
public interface CallBack<T> {

    void onResponse(T result);

    void onFailure(String error);
}
