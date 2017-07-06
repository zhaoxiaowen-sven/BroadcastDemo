package com.example.sven.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by SVEN on 2017/7/6.
 */

public class OrderedReceiver2 extends BroadcastReceiver {
    private static final String TAG = "OrderedReceiver2";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "action = "+intent.getAction());
        Bundle b = new Bundle();
        b.putInt("value", 101);
        setResultExtras(b);
//        abortBroadcast();
    }
}
