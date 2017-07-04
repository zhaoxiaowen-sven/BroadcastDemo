package com.example.sven.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity1";

    private Button bt1, bt2;
    private BroadcastReceiver myReceiver = new MyReceiver();
    private LocalBroadcastManager mLocalBroadcastManager;
    private MyInnerReceiver myInnerReceiver;

    class MyInnerReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case "MyIntent":
                    Log.i(TAG, "receive MyInnerReceiver MyIntent");
                    break;
                case "localManagerIntent":
                    Log.i(TAG, "receive MyInnerReceiver localManagerIntent");
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate...");
        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);

        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);

        initMyReceiver();
        initLocalReceiver();
        initMyInnerReceiver();
    }

    private void initLocalReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("localManagerIntent");
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(myReceiver,intentFilter);
    }

    public void initMyReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyIntent");
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(myReceiver, intentFilter);
    }

    public void initMyInnerReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyIntent");
        intentFilter.addAction("localManagerIntent");
        myInnerReceiver = new MyInnerReceiver();
        registerReceiver(myInnerReceiver, intentFilter);
    }


    @Override
    public void onClick(View v) {
        Log.i(TAG, "MainActivity");
        switch (v.getId()){
            case R.id.bt1:
                Log.i(TAG, "MainActivity");
                sendBroadcast(new Intent("MyIntent"));
                break;
            case R.id.bt2:
                mLocalBroadcastManager.sendBroadcast(new Intent("localManagerIntent"));
                break;
            case R.id.bt3:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
