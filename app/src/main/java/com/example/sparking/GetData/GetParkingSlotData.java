package com.example.sparking.GetData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.myApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetParkingSlotData {
    public GetParkingSlotData(){
        //do nothing
    }

    public GetParkingSlotData(String url,final SuccessCallback successCallback,
                            final FailCallback failCallback){
        System.out.println("------------start GetParkingSlotData");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject obj) {
                System.out.println("----------:" + obj);

                try {
                      JSONArray ja = obj.getJSONArray("Parkingslot");
//                    JSONObject jobject = ja.getJSONObject(0);
//                    System.out.println("有用的内容：" + jobject);
//                    configure.MEJSON = jobject;
//                    configure.changeData();
//                    System.out.println("用户名："+configure.USERNAME+"密码："+configure.PASSWORD);
//                    System.out.println("返回内容：" + configure.MEJSON);
                    if (successCallback != null)
                        successCallback.onSuccess(obj);
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
            }
        });
        // 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
        request.setTag("get data");
        // 将请求加入全局队列中
        myApplication.getHttpQueues().add(request);
    }

    public void GetParkingSlotDataByCarNumber(String carnumber,final SuccessCallback successCallback,
                              final FailCallback failCallback){
        String url=configure.URL_ParkingSlot+"?Parkingslot.car="+carnumber+"&Parkingslot.isempty=0";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject obj) {
                //System.out.println(obj);
                try {
                    JSONArray slots=obj.getJSONArray("Parkingslot");
                    //应该只有一个停车位
                    JSONObject slot=slots.getJSONObject(0);
                    successCallback.onSuccess(slot);
                } catch (JSONException e) {
                    e.printStackTrace();
                    successCallback.onSuccess(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
                System.out.println(error.getMessage());
            }
        });
        // 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
        request.setTag("get slotdata");
        // 将请求加入全局队列中
        myApplication.getHttpQueues().add(request);
    }

    public static interface SuccessCallback {
        void onSuccess(JSONObject obj);
    }

    public static interface FailCallback {
        void onFail();
    }
}

