
package com.example.sven.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by SVEN on 2017/7/3.
 */

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case Intent.ACTION_USER_PRESENT:
                Log.i(TAG, "receive action ACTION_USER_PRESENT");
                break;
            case "MyIntent":
                Log.i(TAG, "receive my intent");
                break;
            case "localManagerIntent":
                Log.i(TAG, "receive my localManagerIntent");
                break;
            case Intent.ACTION_SCREEN_ON:
                Log.i(TAG, "receive my ACTION_SCREEN_ON");
                break;
            case Intent.ACTION_SCREEN_OFF:
                Log.i(TAG, "receive my ACTION_SCREEN_OFF");
                break;
        }
    }
}
