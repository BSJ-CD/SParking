package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.GetData.GetCarData;
import com.example.sparking.GetData.GetParkingInfo;
import com.example.sparking.GetData.configure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button parking, rend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parking = (Button) findViewById(R.id.Parking);
        rend = (Button) findViewById(R.id.Rend);

        DisplayInfo();

        parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParkingActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        rend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RendActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    private void DisplayInfo(){
        /*
        * display user, which car is in park, and fee
        * */

        final ListView parkinginfoList=(ListView) findViewById(R.id.parkinginfoList);

        String userid= configure.USER_ID;
        System.out.println("userid="+userid);
        new GetCarData().getCarByUser("1576571693834", new GetCarData.SuccessCallback() {
            @Override
            public void onSuccess(JSONObject obj) {
                if (obj==null || obj.length()==0){
                    //用户没有车辆数据
                }
                else{
                    //
                    try {
                        JSONArray carList=obj.getJSONArray("Car");
                        //存车信息的list
                        List<Map<String,Object>> carviewlist=new ArrayList<Map<String,Object>>();
                        //看看一共几辆车
                        for (int i=0;i<carList.length();i+=1) {
                            Map<String, Object> map=new HashMap<String,Object>();
                            JSONObject carObj = carList.getJSONObject(i);
                            String carnumber = carObj.getString("carnumber");
                            System.out.println(carnumber);
                            map.put("carnumberView",carnumber);
                            map.put("slotidView","000");

                            //根据车牌号查停车状态



                            carviewlist.add(map);
                        }
                        //绘制车列表的适配器
                        SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,carviewlist,R.layout.adapter_parkinfo,new String[]{"carnumberView","slotidView"},new int[]{R.id.carnumberView,R.id.slotidView});
                        parkinginfoList.setAdapter(adapter);
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



}
