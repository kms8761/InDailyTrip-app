package com.example.dudxk.gpsapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    public CheckableLinearLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    // 체크 상태를 체크변수대로 설정
    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.recordCheckBox);

        if(cb.isChecked() != checked){
            cb.setChecked(checked);
        }
    }

    //현재 체크상태 리턴
    @Override
    public boolean isChecked(){
        CheckBox cb = (CheckBox) findViewById(R.id.recordCheckBox);

        return cb.isChecked();
    }

    // 현재 체크 상태 바꿈
    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.recordCheckBox);

        setChecked(cb.isChecked() ? false : true);
    }
}
