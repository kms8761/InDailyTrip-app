package com.example.dudxk.gpsapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();//파이어스토어하는법


    private EditText mWriteTitleText; //연결해준다
    private EditText mWriteContentsText;
    private EditText mWriteNameText;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        mWriteTitleText = findViewById(R.id.write_title_text);//연결해주는것
        mWriteContentsText = findViewById(R.id.write_contents_text);
        mWriteNameText = findViewById(R.id.write_name_text);

        findViewById(R.id.write_upload_button ).setOnClickListener(this);//find하고 write 버튼 연결하고 this는 art+enter해서 make onclik button


        //이렇게하면 에디트는 준비끝났다
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.write_upload_button) {

            id = mStore.collection("board").document().getId();
            Map<String, Object> post = new HashMap<>();
            post.put("id", "");

            post.put("title", mWriteTitleText.getText().toString());
            post.put("contents", mWriteContentsText.getText().toString());
            post.put("name", mWriteNameText.getText().toString());

            //게시판 쓰게하기
            mStore.collection("board").document(id).set(post)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.e("asdasd", "asdasd");
                            Log.e("asdasd", "asdasd");
                            Log.e("asdasd", "asdasd");
                            Log.e("asdasd", "asdasd");
                            Log.e("asdasd", "asdasd");

                            Toast.makeText(WriteActivity.this, "업로드성공", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("sdfsdf", "adasd");
                    Log.e("asdasd", "asdasd");
                    Log.e("asdasd", "asdasd");
                    Log.e("asdasd", "asdasd");
                    Log.e("asdasd", "asdasd");
                    Log.e("asdasd", "asdasd");

                    Toast.makeText(WriteActivity.this, "업로드 실패", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
