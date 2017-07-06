# BroadcastDemo
##一.注册广播

###1.静态注册：
1. 在AndroidMainfest中添加receiver

        <receiver android:name=".MyReceiver">
            <intent-filter>
                <action android:name="android.intent.action.myreceiver" />
            </intent-filter>
        </receiver>
        
2. 创建类继承BroadcastReceiver的，并且复写onReceive()方法

        public class MyReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "receive action ");
                }
            }
        }

###2. 动态注册
1. 创建广播类和实例

        public class MyReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "receive action ");
                }
            }
        }
        
        private BroadcastReceiver myReceiver = new MyReceiver();
         
2. 创建intentFilter并且添加需要接收的action

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(android.intent.action.myreceiver);

3. 调用registerReceiver方法注册

        registerReceiver(myReceiver, intentFilter);

###Tips：
1. 动态注册的广播可以通过unregisterReceiver取消，
2. action的注册和解注册成对出现在context对应的生命周期中，例如onCreate onDestroy，以及 onResume和onPause
3. 同一个receiver可以同时接收动态注册和静态注册的广播
4. 静态广播在应用没有启动时，是无法接收到的，即使加了Intent.FLAG_INCLUDE_STOPPED_PACKAGES（测试失败）

## 二、广播的类型
###1. 系统广播
   系统广播，当手机状态发生变化时，都会发出相应的系统广播。如：网络状态，解锁等。注意：有的系统广播必须态注册才有效：
   SCREEN_ON，SCREE_OFF
###2. 普通广播
   自定义的广播，通常用于应用内或应用间通信。
###3. 有序广播
1. 多个具当前已经注册且有效的BroadcastReceiver接收有序广播时，是按照先后顺序接收的，先后顺序判定标准遵循为：
将当前系统中所有有效的动态注册和静态注册的BroadcastReceiver按照priority属性值从大到小排序，
对于具有相同的priority的动态广播和静态广播，动态广播会排在前面。
   
    <receiver android:name=".OrderedReceiver1">
        <intent-filter android:priority="100">
            <action android:name="com.sven.action.my.receiver.orderd.receiver" />
        </intent-filter>
    </receiver>
    <receiver android:name=".OrderedReceiver2">
        <intent-filter android:priority="1000">
            <action android:name="com.sven.action.my.receiver.orderd.receiver" />
        </intent-filter>
    </receiver>

 当然动态注册时也支持该做法：
 
    intentFilter.setPriority(1000);
        
2. 先接收的BroadcastReceiver可以对此有序广播进行截断，使后面的BroadcastReceiver不再接收到此广播，
   abortBroadcast

3.receiver之间可以通信
优先接收到Broadcast的Receiver可通过setResultExtras(Bundle)方法将处理结果存入Broadcast中，
下一个Receiver 通过 Bundle bundle=getResultExtras(true)方法获取上一个 Receiver传来的数据 

##三、广播和权限
### 1. 谁有权限接收 (和安装的顺序有关 先装发送者)
    
1. 发送者的manifest声明权限
    
       <permission android:name="com.sven.permission.my.receiver.RECEIVE"/>
    
2. 发送时增加权限
        
        public void sendBroadcast(View view) {
            sendBroadcast(new Intent("com.sven.action.my.receiver"),
                    "com.sven.permission.my.receiver.RECEIVE");
        }
        
3. 接收app的manifest要添加对应的权限
    
        <uses-permission android:name="com.sven.permission.my.receiver.RECEIVE"/>
    
    
### 2. 谁有权限发送 （和安装顺序有关，先安装接收者） 
    1. 接收者的manifest文件中声明权限
       <permission android:name="com.sven.permission.my.receiver.SEND"/>
    
    2. 接收者的receiver节点中添加
       android:permission="com.sven.permission.my.receiver.SEND"
       
    3. 发送者的manifest文件中使用
       <uses-permission android:name="com.sven.permission.my.receiver.SEND"/>

### Tips：
自定义的权限最好在2个app中同时声明，（测试出现了奇怪的情况，生命权限的app，必须先安装，否则还是会报出权限问题），
使用系统权限不会不存在以上问题


### 四、安全高效地使用广播的一些原则：

1.如果不需要发送到应用外，同一个应用内的广播尽量使用LocalBroadcastManager
2.尽量使用动态注册的广播，而且有些系统广播，比如说 CONNECTIVITY_ACTION 在7.0之后只能通过动态注册接收
3.发送广播时明确广播的接受者：
    (1)发送时添加权限
    (2)通过setPackage 指定应用
    (3)使用LocalBroadcastManager
4.广播的命名尽量保证唯一
5.注册一个广播时，限制广播的接收者：
(1)添加一个权限, mainfest 中指定，动态注册的可以使用该接口
    
    public abstract Intent registerReceiver(BroadcastReceiver receiver,
                                                IntentFilter filter, String broadcastPermission,
                                                Handler scheduler);
                                                
(2)仅仅只是应用内使用时，可以使用设置 android:exported="false"。这样就不会接收应用外的广播了
(3)使用LocalBroadcastManager
    
5.onReceive方法运行在主线程中，所以不能执行耗时任务：
 1.使用异步任务
 2.使用JobScheduler. 

6.不要通过广播启动activity，可以使用通知替代

参考资料：
https://developer.android.com/guide/components/broadcasts.html#security_considerations_and_best_practices