package com.toba18419.interview_practice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button question,answer,interview,edit;
    TextView date,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        final Python py = Python.getInstance();
        PyObject pyo = py.getModule("main"); //main.pyの呼び出し
        PyObject date_obj = pyo.callAttr("date");
        PyObject data_obj = pyo.callAttr("data");

        String date_str = date_obj.toString(); //返ってきた値をString型に変換
        String data_str = data_obj.toString();

        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        interview = findViewById(R.id.interview);
        edit = findViewById(R.id.edit);
        date = findViewById(R.id.tweet_date);
        content = findViewById(R.id.tweet_content);

        date.setText(date_str);
        content.setText(data_str);

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