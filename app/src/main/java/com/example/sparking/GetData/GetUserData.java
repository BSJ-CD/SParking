package com.example.sparking.GetData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sparking.myApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserData {
    public void getUserData(String url,final SuccessCallback successCallback,
                               final FailCallback failCallback){
        System.out.println("------------start GetUserData");
        JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, new Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject obj) {
                System.out.println("----------:" + obj);

                try {
                    JSONArray ja = obj.getJSONArray("User");
                    JSONObject jobject = ja.getJSONObject(0);
                    System.out.println("有用的内容：" + jobject);
                    configure.MEJSON = jobject;
                    configure.changeData();
                    System.out.println("用户名："+configure.USERNAME+"密码："+configure.PASSWORD);
                    System.out.println("返回内容：" + configure.MEJSON);
                    if (successCallback != null)
                        successCallback.onSuccess();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    failCallback.onFail();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("----------:error");
                error.getMessage();
                System.out.println(error.getMessage());
                failCallback.onFail();
            }
        });
        // 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
        request.setTag("get data");
        // 将请求加入全局队列中
        myApplication.getHttpQueues().add(request);
    }

    public static interface SuccessCallback {
        void onSuccess();
    }

    public static interface FailCallback {
        void onFail();
    }
}
