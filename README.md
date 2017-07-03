# BroadcastDemo
1.接收广播

静态广播：
1. 在AndroidMainfest中注册
2. 创建类继承BroadcastReceiver的，并且复写onReceive()方法

2.动态广播
1. 创建广播类和实例
2. 创建intentFilter并且添加需要接收的action
3. 调用registerReceiver方法注册

Tips：
1. onReceive 中不能执行耗时任务
2. 动态注册的广播可以通过unregisterReceiver取消，
3. action的注册和解注册成对出现在context对应的生命周期中，例如onCreate onDestroy，以及 onResume和onPause


2.广播的类型
有序广播和无序广播



3.LocalBroadcastReceiver


4.广播和权限
通过在发送广播时添加权限，并且在接收时进行权限验证，更安全

安全高效使用广播的一些原则：

1.如果不需要发送到应用外，同一个应用内的广播尽量使用LocalBroadcastManager
2.尽量使用动态注册的广播，而且有些系统广播，比如说 CONNECTIVITY_ACTION 在7.0之后只能通过动态注册接收
3.发送广播时明确广播的接受者：
    1.发送时声明权限
    
sendBroadcast(new Intent("com.example.NOTIFY"),Manifest.permission.SEND_SMS);
    
2.通过setPackage 指定应用
3.使用LocalBroadcastManager
4.广播的命名尽量保证唯一
5.注册一个广播时，限制广播的接收者：  
    1.添加一个权限

        <receiver android:name=".MyReceiver"
        android:permission="android.mine.permission">
        <intent-filter >
            <action android:name="android.intent.action.CAMERA_BUTTON" />
        </intent-filter>
    </receiver>
    
    2.仅仅只是应用内使用时，可以使用设置 android:exported="false"。这样就不会接收应用外的广播了
    3.使用LocalBroadcastManager
    
5.onReceive方法运行在主线程中，所以不能执行耗时任务：
 1.使用异步任务
 2.使用JobScheduler. 

6.不要通过广播启动activity，可以通过通知替代

参考资料：
https://developer.android.com/guide/components/broadcasts.html#security_considerations_and_best_practices