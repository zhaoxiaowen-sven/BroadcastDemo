
package com.example.sven.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {
    private static final String TAG = "MyReceiver2";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "action " + intent.getAction());
    }
}
