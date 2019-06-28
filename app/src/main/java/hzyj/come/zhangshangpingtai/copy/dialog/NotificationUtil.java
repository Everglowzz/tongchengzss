package hzyj.come.zhangshangpingtai.copy.dialog;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import hzyj.come.zhangshangpingtai.R;


/**
 * Created by Youga on 2015/11/6.
 */
public class NotificationUtil {

    private final Notification notification;
    private NotificationManager notificationMrg;
    Context context;
    PendingIntent pendingIntent;

    public NotificationUtil(Context context) {
        this.context = context;
        notificationMrg = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, context.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        notification = new Notification(R.drawable.icon_logo2,
                "下载新版本", System.currentTimeMillis());
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
    }

    public void displayNotificationMessage(int count) {
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_version);
        contentView.setTextViewText(R.id.n_text, "当前进度" + count + "% ");
        contentView.setProgressBar(R.id.n_progress, 100, count, false);
        notification.contentView = contentView;
        notification.contentIntent = pendingIntent;
        notificationMrg.notify(0, notification);
    }

    public void cancelNotification() {
        notificationMrg.cancel(0);
    }

    
}
