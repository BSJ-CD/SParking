package com.example.sparking.GetData;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.sparking.myApplication;

public class PostUserData {

    public PostUserData(String name, String email, String password,
                        final SuccessCallback successCallback,
                        final FailCallback failCallback) {
        String url = configure.URL_User;
        System.out.println("get url " + url);

        Map<String, String> map = new HashMap<String, String>();
//        if (email != null && !email.equals(""))
//            map.put(configure.KEY_EMAIL, email);
        if (name != null && !name.equals(""))
            map.put(configure.KEY_USERNAME, name);
        if (password != null && !password.equals(""))
            map.put(configure.KEY_PASSWORD, password);
//        map.put(configure.KEY_PHONE, "");
//        map.put(configure.KEY_EDUCATION, "");
//        map.put(configure.KEY_GENDER, "");
//        map.put(configure.KEY_MAJOR, "");
//        map.put(configure.KEY_GTIME, "");
//        map.put(configure.KEY_SCHOOL, "");

        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);

        Log.i("", "finish init the arguments " + url + "  " + map.toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject object) {
                // TODO Auto-generated method stub
                try {
                    System.out.println("内容：" + object);
                    if (successCallback != null)
                        successCallback.onSuccess(object.getString(configure.KEY_ID));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                if (failCallback != null) {
                    failCallback.onFail();
                }
            }
        });

        // 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
        request.setTag("post data");
        // 将请求加入全局队列中
        myApplication.getHttpQueues().add(request);
    }

    public static interface SuccessCallback {
        void onSuccess(String id);
    }

    public static interface FailCallback {
        void onFail();
    }
}
