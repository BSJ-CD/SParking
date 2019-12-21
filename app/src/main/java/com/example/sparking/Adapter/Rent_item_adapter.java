package com.example.sparking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sparking.R;

import java.util.List;

public class Rent_item_adapter extends BaseAdapter
{
    private List<ItemData> mDatas;
    private Context mContext;
    public Rent_item_adapter(Context context, List<ItemData> Datas)
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
        MyViewHolder mViewHolder;

        if(view == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.rent_item,parent,false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        else
        {
            convertView = view;
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
       //TODO: Add something u want to show here.
        mViewHolder.PID.setText(mDatas.get(position).PID);
        mViewHolder.slot_owner_id.setText(mDatas.get(position).slot_owner_id);
        mViewHolder.slot_name.setText(mDatas.get(position).slot_name);
        return convertView;
    }

    private class MyViewHolder
    {
        TextView slot_name, PID, slot_owner_id;
        MyViewHolder(View view)
        {
            slot_name = view.findViewById(R.id.slot_name);
            PID = view.findViewById(R.id.PID);
            slot_owner_id = view.findViewById(R.id.slot_owner_id);
            //TODO: Add something u want to show here.
        }
    }
}
