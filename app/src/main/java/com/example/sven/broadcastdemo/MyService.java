package com.example.sven.broadcastdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.IntDef;

public class MyService extends Service {

    private BroadcastReceiver myBroadcastReceiver = new MyReceiver();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMyReceiver();
    }

    public void initMyReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyIntent");
        registerReceiver(myBroadcastReceiver, intentFilter, "com.sven.permission.my.receiver.SEND", null);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
