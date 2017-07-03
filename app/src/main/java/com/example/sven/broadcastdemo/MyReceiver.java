package com.example.sven.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by SVEN on 2017/7/3.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case Intent.ACTION_CAMERA_BUTTON:
                Log.i(TAG, "receive action");
                break;

        }
    }
}
