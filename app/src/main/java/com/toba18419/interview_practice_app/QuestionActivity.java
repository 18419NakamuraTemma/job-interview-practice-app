package com.toba18419.interview_practice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    ProgressDialog progressDialog;
    DAOQuestions dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        editText = findViewById(R.id.question_entry_content);
        button = findViewById(R.id.question_entry_button);
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if(content.isEmpty()){
                    editText.setError("質問内容を入力してください");
                }
                else{
                    progressDialog.setMessage("登録中です...");
                    progressDialog.setTitle("質問登録");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    Questions question = new Questions("null");
                    dao = new DAOQuestions(content);
                    dao.add(question);
                    Toast.makeText(QuestionActivity.this, "登録が完了しました！", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}