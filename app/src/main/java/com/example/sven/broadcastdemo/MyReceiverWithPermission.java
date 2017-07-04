package com.example.sven.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by SVEN on 2017/7/5.
 */

public class MyReceiverWithPermission extends BroadcastReceiver {

    private static final String TAG = "MyReceiverWithPer";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, intent.getAction());
    }
}
