package com.example.sparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.example.sparking.GetData.GetParkfeeData;
import com.example.sparking.GetData.configure;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PayActivity extends AppCompatActivity {

    private String carnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        carnumber=getIntent().getStringExtra("EXTRA_CAR_NUMBER");
        TextView carnumberTextView=findViewById(R.id.carnumberdisplayView);
        carnumberTextView.append(carnumber);
        TextView timeTextView=findViewById(R.id.timeTextView);
        timeTextView.addTextChangedListener(timetextWatcher);
        DisplayInfo();

    }

    //监听时间的textview是否有变化
    private TextWatcher timetextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            CalculateTime();
        }
    };

    private void CalculateTime(){
        TextView timeTextView=findViewById(R.id.timeTextView);
        TextView alltimeTextView=findViewById(R.id.alltimetextView);
        TextView feeTextView=findViewById(R.id.feeTextView);
        System.out.println(timeTextView);
        String timeString=timeTextView.getText().toString();
        System.out.println(timeString);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begindate= null;
        try {
            begindate = simpleDateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date enddate=new Date(System.currentTimeMillis());
        //System.out.println(simpleDateFormat.format(enddate));
        long endtime=enddate.getTime();
        long begintime=begindate.getTime();
        long parktime=endtime-begintime;

        int hour=(int)(parktime/(1000*60*60));
        int day=hour/24;
        int onedayhour=hour%24;
        int onehourminute=(int)(parktime/(1000*60))%60;
        alltimeTextView.setText(day+"天"+onedayhour+"小时"+onehourminute+"分钟");

        System.out.println(parktime);
        //计算费用
        int halfhour=(int) (parktime/(1000*60*30))%48;
        int fee=CalculateFee(day,halfhour);
        feeTextView.setText(fee+"元");
    }
    private  int CalculateFee(int day,int halfhour){
        return day*100+halfhour*5;
    }




    private void DisplayInfo(){
        final TextView timeTextView=findViewById(R.id.timeTextView);
        new GetParkfeeData().getParkfeeDataByCar(carnumber, new GetParkfeeData.SuccessCallback() {
            @Override
            public void onSuccess(JSONObject obj) {
                System.out.println(obj);
                if (obj==null
                ){
                    //默认调试账号
                    if (configure.USER_ID.equals("000")) {
                        String entertime = "2019-12-12 19:19:40";
                        timeTextView.setText(entertime);
                    }
                    //无此车牌号
                    TextView carnumberTextView=findViewById(R.id.carnumberdisplayView);
                    carnumberTextView.append(":无此车牌号");
                }
                else {
                    String entertime = null;
                    try {
                        entertime = obj.getString("entertime");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    timeTextView.setText(entertime);
                }
            }
        }, new GetParkfeeData.FailCallback() {
            @Override
            public void onFail() {
                //接收失败
                if (configure.USER_ID.equals("000")) {
                    String entertime = "2019-12-12 19:19:42";
                    timeTextView.setText(entertime);
            }
            }
        });


    }
}
