package com.toba18419.interview_practice_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InterViewActivi extends AppCompatActivity {

    Button finish_button,next_button;
    TextView textView;
    int question_number=0;
    List<String> questions;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_view);
        textView = findViewById(R.id.question_interview_content);
        finish_button = findViewById(R.id.finish_interview_button);
        next_button = findViewById(R.id.next_interview_button);
        questions = new ArrayList<String>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    questions.add(snapshot.getKey());
                    temp = datasnapshot.toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        HandlerThread handlerThread = new HandlerThread("foo");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // ここに３秒後に実行したい処理
                if(questions.size() != 0) {
                    textView.setText(questions.get(question_number));

                    next_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            question_number += 1;
                            if (questions.size() > question_number) {
                                textView.setText(questions.get(question_number));
                            } else {
                                Toast.makeText(InterViewActivi.this, "全ての質問が終了しました", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else{
                    textView.setText("質問が登録されていません");
                }
            }
        }, 2000);
    }
}