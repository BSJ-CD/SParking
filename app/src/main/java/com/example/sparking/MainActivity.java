package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sparking.Adapters.ParkinfoAdapter;
import com.example.sparking.GetData.GetCarData;
import com.example.sparking.GetData.GetParkingInfo;
import com.example.sparking.GetData.GetParkingSlotData;
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

    public void searchCar(View view){
        EditText carnumberinputText=(EditText) findViewById(R.id.carnumberinputText);
        String carnumber=carnumberinputText.getText().toString();
        Intent intent = new Intent(this, PayActivity.class);
        intent.putExtra("EXTRA_CAR_NUMBER",carnumber);
        this.startActivity(intent);
    }

    private void DisplayInfo(){
        /*
        * display user, which car is in park, and fee
        * */

        final ListView parkinginfoList=(ListView) findViewById(R.id.parkinginfoList);

        final TextView parkinfotextView=(TextView) findViewById(R.id.trueinfoTextview);
        //final String oldparkinfo=(String) parkinfotextView.getText();

        String userid= configure.USER_ID;
        System.out.println("userid="+userid);
        //存车信息的list
        final List<Map<String,Object>> carviewlist=new ArrayList<Map<String,Object>>();
        //final SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,carviewlist,R.layout.adapter_parkinfo,new String[]{"carnumberView","slotidView"},new int[]{R.id.carnumberView,R.id.slotidView});
        final ParkinfoAdapter adapter=new ParkinfoAdapter(MainActivity.this,carviewlist);
        adapter.notifyDataSetChanged();
        parkinginfoList.setAdapter(adapter);

        //caodian 1576571693834
        new GetParkingInfo().getParkingInfoByUser(userid, new GetParkingInfo.SuccessCallback() {
            @Override
            public void onSuccess(JSONArray obj) {
                if (obj==null || obj.length()==0){
                    //用户没有车辆数据
                    parkinfotextView.setText(":目前无停车");
                }
                else{
                    //
                    try {
                        JSONArray carList=obj;
                        //看看一共几辆车
                        for (int i=0;i<carList.length();i+=1) {
                            final Map<String, Object> map=new HashMap<String,Object>();
                            JSONObject carObj = carList.getJSONObject(i);
                            String carnumber = carObj.getString("carnumber");
                            String carslot=carObj.getString("slotid");
                            int inpark=carObj.getInt("inpark");
                            System.out.println(carnumber+carslot);
                            if (inpark==1) {
                                map.put("carnumber", carnumber);
                                map.put("slotid", carslot);
                                carviewlist.add(map);
                                adapter.notifyDataSetChanged();

                            }
                        }
                        //绘制车列表的适配器

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new GetParkingInfo.FailCallback() {
            @Override
            public void onFail(VolleyError error) {
                System.out.println(error);

                if (configure.USER_ID.equals("000")){
                    final Map<String, Object> map=new HashMap<String,Object>();
                    map.put("carnumber", "NONETWORK");
                    map.put("slotid", "Z00");
                    carviewlist.add(map);
                    adapter.notifyDataSetChanged();
                }
                else{
                    parkinfotextView.setText(":目前无停车");
                }
            }
        });
    }

}
