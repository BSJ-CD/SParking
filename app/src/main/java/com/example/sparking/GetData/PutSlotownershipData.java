package com.example.sparking.GetData;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.myApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class PutSlotownershipData {
    public PutSlotownershipData(String PID, String slotid, long slot_owner_id,
                              final PutSlotownershipData.SuccessCallback successCallback,
                              final PutSlotownershipData.FailCallback failCallback)  {
        String url = configure.URL_Slotownership+PID;
        System.out.println("get url " + url);

        String ps = "{\"slotid\":\""+slotid+"\",\"onrent\":0,\"slotowner\":{\"id\":"+slot_owner_id+"},\"slotrenter\":{\"id\":"+configure.USER_ID+"}}";

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(ps);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------PutSlotownershipData:--------------"+jsonObject);

        Log.i("", "finish init the arguments " + url + "  " + ps);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject object) {
                // TODO Auto-generated method stub

                System.out.println("内容：" + object);
                if (successCallback != null)
                    successCallback.onSuccess();
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
        request.setTag("put Slotownership data");
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
