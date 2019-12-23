package com.example.sparking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sparking.Adapter.Possession_item_adapter;
import com.example.sparking.Bean.ItemData;
import com.example.sparking.Adapter.Rent_item_adapter;
import com.example.sparking.Bean.possessionItem;
import com.example.sparking.GetData.GetSlotownershipData;
import com.example.sparking.GetData.PutSlotownershipData;
import com.example.sparking.GetData.configure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RendActivity extends AppCompatActivity{

    ListView Possession;
    ListView ParkingSlotMarket;
    List<ItemData> Pdata=new ArrayList<>();
    List<possessionItem> PPdata=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rend);

        String url_owner = configure.URL_Slotownership+"?Slotownership.slotowner.id="+configure.USER_ID+"&Slotownership.onrent=-1";
        new GetSlotownershipData(url_owner, new GetSlotownershipData.SuccessCallback() {
            @Override
            public void onSuccess(JSONObject obj) {
                System.out.println("---------------------------"+obj);
                try {
                    JSONArray ja = obj.getJSONArray("Slotownership");

//                    List<ItemData> Pdata=new ArrayList<>();
                    int i=0;
                    while(i<ja.length()){
                        JSONObject jb = ja.getJSONObject(i);
                        String PID  = jb.getString("id");
                        String slotid = jb.getString("slotid");
//                        JSONObject slot_owner = jb.getJSONObject("slotowner");
//                        String slot_owner_id = slot_owner.getString("id");
                        System.out.println(slotid);
                        possessionItem Pinfo = new possessionItem(PID,slotid);
                        PPdata.add(Pinfo);
                        i = i+1;
                    }
                    System.out.println("over loop");
                    Possession =  (ListView) findViewById(R.id.Possession);
                    Possession.setAdapter(new Possession_item_adapter(RendActivity.this, PPdata));
                    Possession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println("----------------------start click-------------------------------");
                            AlertDialog.Builder builder = new AlertDialog.Builder( RendActivity.this);

                            builder.setTitle( "出租车位" );//设置标题
                            builder.setIcon( R.drawable.ic_launcher_background );
                            ListView PSM = (ListView) findViewById(R.id.Possession);
                            final possessionItem itemd = PPdata.get(position);
                            builder.setMessage(itemd.getslot_name());

                            builder.setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText( RendActivity.this,"点击了确定按钮", Toast.LENGTH_SHORT).show();
                                    String PID = itemd.getPID();
                                    String slotid = itemd.getslot_name();
                                    new PutSlotownershipData(PID, slotid, Long.parseLong(configure.USER_ID), 1, new PutSlotownershipData.SuccessCallback() {
                                        @Override
                                        public void onSuccess() {
                                            Toast.makeText(RendActivity.this, "set onrent Success!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(RendActivity.this, RendActivity.class);
                                            startActivity(intent);
                                            RendActivity.this.finish();
                                        }
                                    },new PutSlotownershipData.FailCallback(){

                                        @Override
                                        public void onFail() {
                                            Toast.makeText(RendActivity.this, "set onrent  Fail!", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            } );
                            builder.setNegativeButton( "取消",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText( RendActivity.this,"点击了取消按钮", Toast.LENGTH_SHORT).show();
                                }
                            } );
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new GetSlotownershipData.FailCallback() {
            @Override
            public void onFail() {
                System.out.println("fail");
                Toast.makeText(RendActivity.this, "Get slotownershipdata Failed!", Toast.LENGTH_LONG).show();
            }
        });


//        Possession = (ListView) findViewById(R.id.Possession);
//        String[] data = {"曹典","李蕴哲","金大佬"};
//        Possession.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));

        String url = configure.URL_Slotownership+"?Slotownership.onrent=1";
        new GetSlotownershipData(url, new GetSlotownershipData.SuccessCallback() {
            @Override
            public void onSuccess(JSONObject obj) {
                System.out.println("---------------------------"+obj);
                try {
                    JSONArray ja = obj.getJSONArray("Slotownership");

//                    List<ItemData> Pdata=new ArrayList<>();
                    int i=0;
                    while(i<ja.length()){
                        JSONObject jb = ja.getJSONObject(i);
                        String PID  = jb.getString("id");
                        String slotid = jb.getString("slotid");
                        JSONObject slot_owner = jb.getJSONObject("slotowner");
                        String slot_owner_id = slot_owner.getString("id");
                        System.out.println(slotid);
                        ItemData Pinfo = new ItemData(PID,slot_owner_id,slotid);
                        Pdata.add(Pinfo);
                        i = i+1;
                    }
                    System.out.println("over loop");
                    ParkingSlotMarket =  (ListView) findViewById(R.id.ParkingSlotMarket);
                    ParkingSlotMarket.setAdapter(new Rent_item_adapter(RendActivity.this, Pdata));
                    ParkingSlotMarket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println("----------------------start click-------------------------------");
                            AlertDialog.Builder builder = new AlertDialog.Builder( RendActivity.this);

                            builder.setTitle( "租用车位" );//设置标题
                            builder.setIcon( R.drawable.ic_launcher_background );
                            ListView PSM = (ListView) findViewById(R.id.ParkingSlotMarket);
//        final String m = (String)PSM.getItemAtPosition(position);
                            final ItemData itemd = Pdata.get(position);
                            builder.setMessage(itemd.getslot_name());

                            builder.setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText( RendActivity.this,"点击了确定按钮", Toast.LENGTH_SHORT).show();
                                    String PID = itemd.getPID();
                                    String slotid = itemd.getslot_name();
                                    String slot_owner_id = itemd.getslot_owner_id();
                                    new PutSlotownershipData(PID, slotid, Long.parseLong(slot_owner_id), 0, new PutSlotownershipData.SuccessCallback() {
                                        @Override
                                        public void onSuccess() {
                                            Toast.makeText(RendActivity.this, "rent Success!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(RendActivity.this, RendActivity.class);
                                            startActivity(intent);
                                            RendActivity.this.finish();
                                        }
                                    },new PutSlotownershipData.FailCallback(){

                                        @Override
                                        public void onFail() {
                                            Toast.makeText(RendActivity.this, "rent Fail!", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            } );
                            builder.setNegativeButton( "取消",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText( RendActivity.this,"点击了取消按钮", Toast.LENGTH_SHORT).show();
                                }
                            } );
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new GetSlotownershipData.FailCallback() {
            @Override
            public void onFail() {
                System.out.println("fail");
                Toast.makeText(RendActivity.this, "Get slotownershipdata Failed!", Toast.LENGTH_LONG).show();
            }
        });
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//        AlertDialog.Builder builder = new AlertDialog.Builder( RendActivity.this);
//
//        builder.setTitle( "租用车位" );//设置标题
//        builder.setIcon( R.drawable.ic_launcher_background );
//        ListView PSM = (ListView) findViewById(R.id.ParkingSlotMarket);
////        final String m = (String)PSM.getItemAtPosition(position);
//        final ItemData itemd = Pdata.get(position);
//        builder.setMessage(itemd.getslot_name());
//
//        builder.setPositiveButton( "确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                Toast.makeText( RendActivity.this,"点击了确定按钮", Toast.LENGTH_SHORT).show();
//                String PID = itemd.getPID();
//                String slotid = itemd.getslot_name();
//                String slot_owner_id = itemd.getslot_owner_id();
//                new PutSlotownershipData(PID, slotid, Long.parseLong(slot_owner_id), new PutSlotownershipData.SuccessCallback() {
//                    @Override
//                    public void onSuccess() {
//                        Toast.makeText(RendActivity.this, "rent Success!", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(RendActivity.this, RendActivity.class);
//                        startActivity(intent);
//                        RendActivity.this.finish();
//                    }
//                },new PutSlotownershipData.FailCallback(){
//
//                    @Override
//                    public void onFail() {
//                        Toast.makeText(RendActivity.this, "rent Fail!", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        } );
//        builder.setNegativeButton( "取消",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText( RendActivity.this,"点击了取消按钮", Toast.LENGTH_SHORT).show();
//            }
//        } );
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//    }

}
