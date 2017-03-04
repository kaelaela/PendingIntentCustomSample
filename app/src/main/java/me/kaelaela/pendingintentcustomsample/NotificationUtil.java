package me.kaelaela.pendingintentcustomsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtil {
    public static void showNormalNotification(Context context) {
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context.getApplicationContext());
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context.getApplicationContext());
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle("normal").setTicker("ticker").setContentText("nomal");
        notificationBuilder.setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle()
                .setBigContentTitle("normal").bigText("normal"));

        Intent topIntent = new Intent(context, TopActivity.class);
        taskStackBuilder.addNextIntent(topIntent);
        PendingIntent pi = PendingIntent.getActivities(context, 0, taskStackBuilder.getIntents(), PendingIntent.FLAG_CANCEL_CURRENT);
        notificationBuilder.setFullScreenIntent(pi, true);
        notificationBuilder.setContentIntent(pi);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = notificationBuilder.build();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
            notification.fullScreenIntent = null;
        }
        notificationManager.notify(0, notification);
    }

    public static void showCustomNotification(Context context) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context.getApplicationContext());
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle("custom").setTicker("ticker").setContentText("custom");
        notificationBuilder.setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle()
                .setBigContentTitle("custom").bigText("custom"));

        //TaskStackBuilderを使わずに自分でIntentの配列を作成しflagをIntent.FLAG_ACTIVITY_NEW_TASKへ
        Intent topIntent = new Intent(context, TopActivity.class);
        Intent[] intents = new Intent[1];
        intents[0] = topIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivities(context, 0, intents, PendingIntent.FLAG_CANCEL_CURRENT);
        notificationBuilder.setFullScreenIntent(pi, true);
        notificationBuilder.setContentIntent(pi);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = notificationBuilder.build();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
            notification.fullScreenIntent = null;
        }
        notificationManager.notify(1, notification);
    }
}
