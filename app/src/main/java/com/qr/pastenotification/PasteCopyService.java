package com.qr.pastenotification;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * ====================== 监听 粘贴板 服务========================
 * 用于监听 剪贴板的变化
 * 若剪贴板发生变化 则发送 "PasteCopy.com" 的广播，PasteCopyReceiver会接收并处理
 * @author SGamble
 */
public class PasteCopyService extends Service {
    private ClipboardManager clipboardManager;
    private final String TAG = "PasteCopyService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerClipEvents();
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    private void registerClipEvents() {
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                String content = clipboardManager.getText()+"";

                Log.e("onPrimaryClipChanged: ", content);

                //发送广播
                Intent intent = new Intent();
                intent.setAction("PasteCopy.com");
                sendBroadcast(intent);
            }
        });
    }

}
