package com.example.dudxk.gpsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecordActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    int index;
    int recordIndex;
    int selectItemIndex;
    String selectTitle,selectDate;
    String recordTitle;
    String recordDate;
    String f_lat,f_lng;
    String firstlat, firstlng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        final TextView textview1 = (TextView)findViewById(R.id.RecordTitle);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mReference = FirebaseDatabase.getInstance().getReference();

        final ListView listview;
        final RecordListViewAdapter adapter;

        // Adapter 생성
        adapter = new RecordListViewAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.RecordListView);
        listview.setAdapter(adapter);

        initDatabase();

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recordIndex = Integer.parseInt(dataSnapshot.child("recordnum").getValue().toString());
                for(int i=0; i<recordIndex; i++) {
                        recordTitle = dataSnapshot.child("여행기록").child(String.valueOf(i)).child("titlestr").getValue().toString();
                        recordDate = dataSnapshot.child("여행기록").child(String.valueOf(i)).child("datestr").getValue().toString();

                        adapter.addItem(recordTitle, recordDate);
                        adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listview.setSelection(adapter.getCount()-1);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                RecordListViewItem item = (RecordListViewItem) parent.getItemAtPosition(position);
                String titleStr = item.getTitle();
                String dateStr = item.getDate();

                selectTitle = titleStr;
                selectDate = dateStr;
                selectItemIndex = position;

            }
        });

        ImageButton button1 = (ImageButton) findViewById(R.id.ShowCourse);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mFirst;

                mFirst = mReference.child("gps").child(String.valueOf(selectItemIndex)).child("polyposition").child("0");
                Log.d("selectItemIndex",String.valueOf(selectItemIndex));

                mFirst.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("dataSnapshot",String.valueOf(dataSnapshot));
                        f_lat = dataSnapshot.child("lat").getValue().toString();
                        f_lng = dataSnapshot.child("lng").getValue().toString();
                        Log.d("f_lat",String.valueOf(f_lat));
                        Log.d("f_lng",String.valueOf(f_lng));

                        Intent intent = new Intent(RecordActivity.this, ShowMapActivity.class);

                        firstlatlng(f_lat, f_lng);
                        intent.putExtra("selectItemIndex",String.valueOf(selectItemIndex));
                        intent.putExtra("firstlat",firstlat);
                        Log.d("firstlat",String.valueOf(firstlat));
                        intent.putExtra("firstlng",firstlng);
                        startActivity(intent);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        ImageButton button2 = (ImageButton) findViewById(R.id.ModifyTitle);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터 담은후 수정팝업창 호출
                Intent intent = new Intent(RecordActivity.this,ModifyPopupActivity.class);
                intent.putExtra("titleText",selectTitle);
                intent.putExtra("selectItemIndex",String.valueOf(selectItemIndex));
                startActivityForResult(intent,1);
                adapter.listViewItemList.clear();
                adapter.notifyDataSetChanged();
            }

        });

        ImageButton button3 = (ImageButton) findViewById(R.id.UploadCourse);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this,minseok.class);
                intent.putExtra("titleText",selectTitle);
                intent.putExtra("dateText",selectDate);
                intent.putExtra("selectItemIndex",String.valueOf(selectItemIndex));
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기

                selectTitle = data.getStringExtra("title");
                mDatabase.child("여행기록").child(String.valueOf(selectItemIndex)).child("titlestr").setValue(selectTitle);

            }
        }
    }

    private void initDatabase() {

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    public void firstlatlng(String startlat, String startlng){
        firstlat = startlat;
        firstlng = startlng;
        Log.d("in function firstlat",String.valueOf(firstlat));
        Log.d("in function firstlng",String.valueOf(firstlng));
    }
}
