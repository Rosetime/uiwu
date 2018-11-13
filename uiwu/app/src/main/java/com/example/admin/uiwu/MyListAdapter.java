package com.example.admin.uiwu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MyListAdapter extends BaseAdapter implements AdapterView.OnClickListener {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Object> arrayList;


    public MyListAdapter(Context context, ArrayList<Object> arrayList){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.arrayList=arrayList;
    }

    public MyListAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    static class ViewHoIder{
        public ImageView imageView;
        public TextView tvTile,tvTime,tvContent;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoIder hoIder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item,null);
            hoIder = new ViewHoIder();
            hoIder.imageView = convertView.findViewById(R.id.iv);
            hoIder.tvContent = convertView.findViewById(R.id.tv_content);
            hoIder.tvTile = convertView.findViewById(R.id.tv_title);
            hoIder.tvTime = convertView.findViewById(R.id.tv_time);
            convertView.setTag(hoIder);
        }else{
            hoIder = (ViewHoIder) convertView.getTag();
        }


        ShuJu shuJu = (ShuJu) arrayList.get(position);
        hoIder.imageView.setTag(R.id.tag_glide,position);
        Glide.with(mContext).load(shuJu.GetTu()).into(hoIder.imageView);
        hoIder.tvTile.setText("这是标题恩"+shuJu.GetTime());
        hoIder.tvTime.setText("2018-5-16"+" 第"+position+"条恩");
        hoIder.tvContent.setText("这是内容oa"+shuJu.GetNa());
        addListener(hoIder);

        return convertView;
    }

    private void addListener(ViewHoIder hoIder){
        hoIder.imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int position = (Integer) view.getTag(R.id.tag_glide);
        switch (view.getId()){
            case R.id.iv:
                Log.e("nihk","hello "+position);
                Toast.makeText(mContext,""+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext,sbsoActivity.class);
                mContext.startActivity(intent);
                break;
        }

    }
}
