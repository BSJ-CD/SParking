package com.example.sparking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sparking.Bean.possessionItem;
import com.example.sparking.R;

import java.util.List;

public class Possession_item_adapter  extends BaseAdapter {

    private List<possessionItem> mDatas;
    private Context mContext;
    public Possession_item_adapter(Context context, List<possessionItem> Datas)
    {
        this.mContext = context;
        this.mDatas = Datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View convertView;
        Possession_item_adapter.MyViewHolder mViewHolder;

        if(view == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.possession,parent,false);
            mViewHolder = new Possession_item_adapter.MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        else
        {
            convertView = view;
            mViewHolder = (Possession_item_adapter.MyViewHolder) convertView.getTag();
        }
        //TODO: Add something u want to show here.
        mViewHolder.PID.setText(mDatas.get(position).getPID());
        mViewHolder.slot_name.setText(mDatas.get(position).getslot_name());
        return convertView;
    }

    private class MyViewHolder
    {
        TextView slot_name, PID;
        MyViewHolder(View view)
        {
            slot_name = view.findViewById(R.id.slot_name);
            PID = view.findViewById(R.id.PID);
            //TODO: Add something u want to show here.
        }
    }

}
