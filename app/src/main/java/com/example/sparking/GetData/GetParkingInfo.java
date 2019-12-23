package com.example.sparking.GetData;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.LoginActivity;
import com.example.sparking.MainActivity;

import com.example.sparking.myApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetParkingInfo {
    public void getParkingInfoByUser(String userid,final SuccessCallback successCallback,
                               final FailCallback failCallback){

        //查询用户的车辆
            String url = configure.URL_ParkInfo+"?Parkinfo.userid="+userid;

            System.out.println(url);
            //查询用户的车辆
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject obj) {
                            System.out.println("Response: " + obj.toString());
                            try {
                                JSONArray ja=obj.getJSONArray("Parkinfo");
                                successCallback.onSuccess(ja);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                successCallback.onSuccess(null);
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            failCallback.onFail(error);
                        }
                    });

            // 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
            jsonObjectRequest.setTag("getparkinfo");
            // Access the RequestQueue
            myApplication.getHttpQueues().add(jsonObjectRequest);


    }

    public static interface SuccessCallback {
        void onSuccess(JSONArray obja);
    }

    public static interface FailCallback {
        void onFail(VolleyError error);
    }
}
