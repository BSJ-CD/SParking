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
        String url=configure.URL_ParkFee+"?ParkFee.carnumber="+carnumber+"&Parkfee.left=0";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject obj) {
                //System.out.println(obj);
                try {
                    JSONArray objArray=obj.getJSONArray("Parkfee");
                    //至多一个入场时间数据
                    JSONObject object=objArray.getJSONObject(0);
                    successCallback.onSuccess(object);
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
        request.setTag("get parkfee");
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
