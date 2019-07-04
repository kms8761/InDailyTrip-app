package com.example.dudxk.gpsapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ModifyPopupActivity extends Activity{

    private DatabaseReference mDatabase;
    EditText edittext;
    String titleText;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_modifypopup);

        edittext = (EditText)findViewById(R.id.EditTitle);

        String text1;

        // 데이터 가져오기
        Intent intent = getIntent();
        titleText = intent.getStringExtra("titleText");
    }

    // 확인 버튼 클릭시
    public void mOnClose(View v){


        //데이터 전달하기
        Intent intent = new Intent();
        titleText = edittext.getText().toString();
        intent.putExtra("title",titleText);
        setResult(RESULT_OK,intent);

        finish();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //바깥클릭시 안닫히게
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){

    }
}
