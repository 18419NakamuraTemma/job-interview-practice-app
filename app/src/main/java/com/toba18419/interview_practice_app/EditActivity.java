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

public class EditActivity extends AppCompatActivity {

    TextView question,answer;
    int question_number = 0;
    Button finish_button,next_button;
    List<String> questions,answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        question = findViewById(R.id.question_edit_content);
        answer = findViewById(R.id.answer_edit_content);
        next_button = findViewById(R.id.next_edit_button);
        finish_button = findViewById(R.id.finish_edit_button);
        answers  = new ArrayList<String>();
        questions  = new ArrayList<String>();


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_number += 1;
            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    questions.add(snapshot.getKey());
                    Questions que = snapshot.getValue(Questions.class);
                    answers.add(que.getContent());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        HandlerThread handlerThread = new HandlerThread("foo");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // ここに３秒後に実行したい処理
                if(questions.size() != 0) {
                    question.setText(questions.get(question_number));
                    answer.setText(answers.get(question_number));

                    next_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            question_number += 1;
                            if (questions.size() > question_number) {
                                question.setText(questions.get(question_number));
                                answer.setText(answers.get(question_number));
                            } else {
                                Toast.makeText(EditActivity.this, "全ての質問が終了しました", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else{
                    question.setText("質問が登録されていません");
                    answer.setText("回答が登録されていません");
                }
            }
        }, 2000);
    }
}