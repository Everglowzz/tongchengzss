package hzyj.come.zhangshangpingtai.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;


public class SpManager {

    private final SharedPreferences mSharedPreferences;

  
    public SpManager(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public <T> void setCommon(String key, T value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        editor.apply();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public void cleanCache(String... keys) {
        HashMap<String, String> params = new HashMap<>();
        for (String key : keys) {
            params.put(key, mSharedPreferences.getString(key, ""));
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        for (String key : keys) {
            editor.putString(key, params.get(key));
        }
        editor.apply();
    }
}
