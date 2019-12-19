package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sparking.GetData.GetParkingSlotData;
import com.example.sparking.GetData.configure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RendActivity extends AppCompatActivity {

    ListView Possession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rend);

        Possession = (ListView) findViewById(R.id.Possession);
        String[] data = {"曹典","李蕴哲","金大佬"};
        Possession.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));

        String url = configure.URL_ParkingSlot;
        new GetParkingSlotData(url, new GetParkingSlotData.SuccessCallback() {
            @Override
            public void onSuccess(JSONObject obj) {
                System.out.println("---------------------------"+obj);
                try {
                    JSONArray ja = obj.getJSONArray("Parkingslot");
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new GetParkingSlotData.FailCallback() {
            @Override
            public void onFail() {
                System.out.println("fail");
                Toast.makeText(RendActivity.this, "Login Failed!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
