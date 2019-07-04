package com.example.dudxk.gpsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import static com.example.dudxk.gpsapplication.R.id.main_wirte_button;


//
public class minseok extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mMainRecyclerView;

   // private Mainadapter mAdapter;
    private List<board> mboardList;
    private Button writebtn;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minseok);
        mMainRecyclerView = findViewById(R.id.main_recycler_view);
        floatingActionButton = findViewById(R.id.main_wirte_button);
        //findViewById(R.id.main_wirte_button).setOnClickListener(this);

        mboardList = new ArrayList<>();
        List<board> mboardList = new ArrayList<>();

        /////제거 해야하는부분

        mStore.collection("board")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    Mainadapter mAdapter;
                    List<board> mboardList = new ArrayList<>();
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {

                            String id = (String) dc.getDocument().get("id");
                            String title = (String) dc.getDocument().get("title");
                            String contents = (String) dc.getDocument().get("contents");
                            String name = (String) dc.getDocument().get("name");
                            board data = new board(id, title, contents, name);

                            mboardList.add(data);
                        }

                        mAdapter = new Mainadapter(mboardList);
                        mMainRecyclerView.setAdapter(mAdapter);

                    }
                });

        // 여기서 여기까지

    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.main_wirte_button:
                startActivity(new Intent(this, WriteActivity.class));
            default:
                break;
        }
    }

    private class Mainadapter extends RecyclerView.Adapter<Mainadapter.MainViewHolder> {

        private List<board> mboardList;

        public Mainadapter(){ }

        public Mainadapter(List<board> mboardList)
        {
            this.mboardList = mboardList;
            //이런거 신경 안써도돼 빨간줄 안뜨면 일단 넘겨 ;;아이템메인 어캄 그거 새
        }
        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new MainViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder , int position) {
            board data = mboardList.get(position);
            mainViewHolder.mTitleTextiew.setText(data.getTitle());
            mainViewHolder.mNameTextView.setText(data.getName());
            mainViewHolder.mContentsTextView.setText(data.getContents());
        }

        @Override
        public int getItemCount() {

            //Log.e("itemCount :", String.valueOf(mboardLists.size()));로그로 찍어서하는법
            return mboardList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {
            private TextView mContentsTextView;
            private TextView mTitleTextiew;
            private TextView mNameTextView;
            public MainViewHolder(@NonNull View itemView) {
                super(itemView);
                mTitleTextiew = itemView.findViewById(R.id.item_title_text);
                mNameTextView = itemView.findViewById(R.id.item_name_text);
                mContentsTextView = itemView.findViewById(R.id.item_contents_text);

                //  if(mTitleTextiew == null || mNameTextView == null) Log.e("Log","TextView not found");로그로 찍어서하는법
            }
        }

    }}
