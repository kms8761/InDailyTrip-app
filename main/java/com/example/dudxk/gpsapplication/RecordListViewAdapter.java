package com.example.dudxk.gpsapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordListViewAdapter extends BaseAdapter {

    // Adapter에 추가된 테이터를 저장하기 위한 ArrayList
    final ArrayList<RecordListViewItem> listViewItemList = new ArrayList<RecordListViewItem>();

    // RecordListViewAdapter의 생성자
    public RecordListViewAdapter(){

    }

    //Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        // RecordListView_item  Layout을 inflate하여 convertView 참조 획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.record_listview_item,parent,false);
        }

        //화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.textView2);

        // RecordListViewItem 리스트에서 position에 위치한 데이터 참조 획득
        RecordListViewItem listViewItem = listViewItemList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(listViewItem.getTitle());
        dateTextView.setText(listViewItem.getDate());

        return convertView;
    }

    // 지정한 위치에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position){
        return position;
    }

    // 지정한 위치에 있는 데이터 리턴
    public Object getItem(int position){
        return listViewItemList.get(position);
    }


    public void addItem(String title, String data){
        RecordListViewItem item = new RecordListViewItem();

        item.setTitle(title);
        item.setDate(data);

        listViewItemList.add(item);
    }
}
