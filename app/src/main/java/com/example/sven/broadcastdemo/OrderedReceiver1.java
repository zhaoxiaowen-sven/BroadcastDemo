package com.example.sven.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by SVEN on 2017/7/6.
 */

public class OrderedReceiver1 extends BroadcastReceiver {
    private static final String TAG = "OrderedReceiver1";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "action = "+intent.getAction());
        Bundle b = getResultExtras(true);
        Log.i(TAG,"value = "+b.get("value"));
    }
}
