package com.example.sparking.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sparking.PayActivity;
import com.example.sparking.R;

import static androidx.core.content.ContextCompat.startActivity;

public class ParkinfoAdapter extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater mInflater;
    private Context context;

    public ParkinfoAdapter(Context context,List<Map<String,Object>> data){
        this.context=context;
        this.mInflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public int getCount() {
        return data == null ?0:data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public final class ViewHolder{
        public TextView carnumbertext;
        public TextView slotidtext;
        public TextView timetext;
        public Button payBtn;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;            //首先定义一个ViewHolder对象
        if (convertView == null) {            //当初始化的时候 convertView是空的

            holder=new ViewHolder();        //创建一个ViewHolder

            //通过LayoutInflater用来找res/layout/下的xml布局文件，并且实例化，这样convertView的界面就有了。
            convertView = mInflater.inflate(R.layout.adapter_parkinfo, null);
            //使用Activiyt.findViewById()方法来获得其中的界面元素。
            holder.carnumbertext = (TextView) convertView.findViewById(R.id.carnumberView);
            holder.slotidtext = (TextView)convertView.findViewById(R.id.slotidView);
            holder.timetext = (TextView)convertView.findViewById(R.id.timeTextView);
            holder.payBtn = (Button)convertView.findViewById(R.id.payButton);
            //将holder对象作为标签添加到View上
            convertView.setTag(holder);

        }else {
            //不为空的时候直接重新使用convertView从而减少了很多不必要的View的创建
            holder = (ViewHolder)convertView.getTag();
        }

        holder.carnumbertext.setText("车牌号"+(String)data.get(i).get("carnumber"));
        holder.slotidtext.setText("车位"+(String)data.get(i).get("slotid"));
        holder.timetext.setText((String)data.get(i).get("time"));

        final String carnumber=(String)data.get(i).get("carnumber");
        holder.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PayActivity.class);
                intent.putExtra("EXTRA_CAR_NUMBER",carnumber);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
