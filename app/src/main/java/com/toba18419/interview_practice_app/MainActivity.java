package com.toba18419.interview_practice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button question,answer,interview,edit;
    TextView date,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        interview = findViewById(R.id.interview);
        edit = findViewById(R.id.edit);
        date = findViewById(R.id.tweet_date);
        content = findViewById(R.id.tweet_content);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), QuestionActivity.class);
                startActivity(intent);
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), AnswerActivity.class);
                startActivity(intent);
            }
        });

        interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InterViewActivi.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), EditActivity.class);
                startActivity(intent);
            }
        });
    }
}