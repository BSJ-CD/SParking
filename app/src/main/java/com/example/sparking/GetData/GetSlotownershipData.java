package com.example.sparking.GetData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.myApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetSlotownershipData {
    public GetSlotownershipData(String url,final GetSlotownershipData.SuccessCallback successCallback,
                              final GetSlotownershipData.FailCallback failCallback){
        System.out.println("------------start GetSlotownershipData");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject obj) {
                System.out.println("----------:" + obj);

                try {
                    JSONArray ja = obj.getJSONArray("Slotownership");
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
        request.setTag("get Slotownership data");
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
