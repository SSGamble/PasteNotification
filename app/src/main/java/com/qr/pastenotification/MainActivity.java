package com.qr.pastenotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //========================== 启动粘贴板服务 ==========================
        Intent startIntent = new Intent(this, PasteCopyService.class);
        startService(startIntent);
    }
}
