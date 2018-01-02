package com.qr.pastenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * ====================== 剪贴板 广播========================
 * 接收到 “PasteCopy.com” 则显示 通知，
 * 点击通知则发送 “AddTemp.com” NotificationBroadcastReceiver会接收广播，
 * 然后追加临时笔记（数据库）
 * @author SGamble
 */
public class PasteCopyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("PasteCopy.com")) {
            showNotification(context);
        }
    }

    /**
     * 显示 追加临时笔记 的通知
     * @param context
     */
    private void showNotification(Context context) {
        //========================== 发送 “AddTemp”的广播 ==========================
        Intent intentClick = new Intent(context, NotificationBroadcastReceiver.class);
        intentClick.setAction("AddTemp.com");
        PendingIntent pendingIntentClick = PendingIntent.getBroadcast(context, 900, intentClick, PendingIntent.FLAG_CANCEL_CURRENT);

        //========================== PendingIntent 延迟跳转 ==========================
//        Intent intent = new Intent(context, ToDoListActivity.class);
//        PendingIntent pendingIntentClick = PendingIntent.getActivity(context, 0, intent , 0);

        //========================== 显示通知 ==========================
        //1.获取 NotificationManager 对象，用于对通知进行管理
        NotificationManager manager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        //2.使用 Builder 构造器来创建 Notification 对象
        Notification notification = new android.support.v4.app.NotificationCompat.Builder(context)
                .setContentTitle("onPrimaryClipChanged")
                .setContentText("是否追加到临时笔记？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntentClick)//点击操作
                .setAutoCancel(true)//自动消失
                .setPriority(Notification.PRIORITY_HIGH) //重要级别
                .build();
        //3.显示通知
        manager.notify(1, notification);
    }
}
