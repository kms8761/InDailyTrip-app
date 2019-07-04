package com.example.dudxk.gpsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by webnautes on 2017-11-27.
 */

public class NewActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        String title = "";
        String address = "";

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            title = "error";
        }
        else {
            title = extras.getString("title");
            address = extras.getString("address");
        }

        TextView textView = (TextView) findViewById(R.id.textView_newActivity_contentString);

        String str = title + '\n' + address + '\n';
        textView.setText(str);

    }
}
