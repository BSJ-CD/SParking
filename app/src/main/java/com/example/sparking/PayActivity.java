package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sparking.GetData.GetParkfeeData;

import org.json.JSONException;
import org.json.JSONObject;

public class PayActivity extends AppCompatActivity {

    private String carnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        carnumber=getIntent().getStringExtra("EXTRA_CAR_NUMBER");

        DisplayInfo();


    }

    private void DisplayInfo(){
        final TextView timeTextView=(TextView)findViewById(R.id.timeTextView);
        final TextView feeTextView=(TextView)findViewById(R.id.feeTextView);


        new GetParkfeeData().getParkfeeDataByCar(carnumber, new GetParkfeeData.SuccessCallback() {
            @Override
            public void onSuccess(JSONObject obj) {
                System.out.println(obj);
                String entertime=null;
                try {
                    entertime=obj.getString("entertime");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                timeTextView.setText(entertime);
            }
        }, new GetParkfeeData.FailCallback() {
            @Override
            public void onFail() {
                //接收失败
            }
        });


    }
}
