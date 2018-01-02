package com.qr.pastenotification;

import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * ====================== 通知 广播 ========================
 * 接收"AddTemp.com"的广播
 *  追加临时笔记
 * @author SGamble
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("AddTemp.com")) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
            addTemp(context,clipboardManager.getText()+"");
        }
    }

    /**
     * 追加 临时笔记
     * @param content
     */
    private void addTemp(Context context,String content) {
        Log.e("addTemp: ", content);
    }
}
