package com.example.sparking.GetData;


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
        new GetCarData().getCarByUser(userid, new GetCarData.SuccessCallback() {
            @Override
            public void onSuccess(JSONObject obj) {
                if (obj==null || obj.length()==0){
                    //用户没有车辆数据
                    successCallback.onSuccess(obj);
                }
                else{
                    //
                    try {
                        JSONArray carList=obj.getJSONArray("Car");
                        //目前只有一辆车for (int i=0;i<carList.length();i+=1){
                        JSONObject carObj=carList.getJSONObject(0);
                        carObj.getString("carnumber");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new GetCarData.FailCallback() {
            @Override
            public void onFail(VolleyError error) {
                System.out.println(error);
            }
        });


    }

    public static interface SuccessCallback {
        void onSuccess(JSONObject obj);
    }

    public static interface FailCallback {
        void onFail();
    }
}
