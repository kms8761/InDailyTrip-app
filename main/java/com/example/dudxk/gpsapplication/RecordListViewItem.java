package com.example.dudxk.gpsapplication;

public class RecordListViewItem {
    private String titleStr;
    private String recordDate;

    public void setTitle(String title){
        titleStr = title;
    }

    public void setDate(String date){
        recordDate = date;
    }

    public String getTitle(){
        return this.titleStr;
    }

    public String getDate(){
        return this.recordDate;
    }
}
