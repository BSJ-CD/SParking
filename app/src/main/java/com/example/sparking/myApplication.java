package com.example.sparking;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 1、为了打开客户端的日志，便于在开发过程中调试，需要自定义一个 Application。
 * 并将自定义的 application 注册在 AndroidManifest.xml 文件中。<br/>
 * 2、为了提高 push 的注册率，您可以在 Application 的 onCreate 中初始化 push。你也可以根据需要，在其他地方初始化 push。
 *
 * @author wangkuiwei
 */
public class myApplication extends Application {

    public static RequestQueue queue;

    public static final String TAG = "com.lixuce.zhaopin";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }


    /**
     * 获取全局请求队列
     *
     * @return
     */
    public static RequestQueue getHttpQueues() {
        return queue;
    }
}
