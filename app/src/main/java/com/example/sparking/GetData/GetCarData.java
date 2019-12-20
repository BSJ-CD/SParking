package com.example.sparking.GetData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.myApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetCarData {
    public void getCarByUser(String userid,final SuccessCallback successCallback,
                               final FailCallback failCallback){
        String url = configure.URL_Car+"?Car.carownership.id="+userid;

        System.out.println(url);


        //查询用户的车辆
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject obj) {
                        System.out.println("Response: " + obj.toString());
                        successCallback.onSuccess(obj);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        failCallback.onFail(error);
                    }
                });

        // 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
        jsonObjectRequest.setTag("getCarData");
        // Access the RequestQueue.
        myApplication.getHttpQueues().add(jsonObjectRequest);

    }

    public static interface SuccessCallback {
        void onSuccess(JSONObject obj);
    }

    public static interface FailCallback {
        void onFail(VolleyError error);
    }


}
