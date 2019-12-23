package com.example.sparking.GetData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.myApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetParkfeeData {


    //根据车牌号查找停车时间，返回最多一条数据
    public void getParkfeeDataByCar(String carnumber,final SuccessCallback successCallback,
                                              final FailCallback failCallback){
        String url = configure.URL_ParkFee+"?Parkfee.carnumber="+carnumber;

        System.out.println(url);
        //查询用户的车辆
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject obj) {
                        System.out.println("Response: " + obj.toString());
                        try {
                            JSONArray ja=obj.getJSONArray("Parkfee");
                            successCallback.onSuccess(ja.getJSONObject(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            successCallback.onSuccess(null);
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        successCallback.onSuccess(null);
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
        void onFail();
    }

}
