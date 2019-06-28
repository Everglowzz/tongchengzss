package hzyj.come.zhangshangpingtai.utlis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EverGlow on 2019/1/10 11:24
 */

public class MyUtils {

    public static String getDateToString(long milSecond) {
        if (milSecond <= 0) {
            return "";
        }
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getDateToString(String milSecond) {

        long second = 0;
        try {
            second = Long.parseLong(milSecond);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return getDateToString(second);
    }

}
