package com.toba18419.interview_practice_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnswerActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    Spinner spinner;
    ProgressDialog progressDialog;
    DAOQuestions dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        editText = findViewById(R.id.answer_entry_content);
        button = findViewById(R.id.answer_entry_button);
        spinner = findViewById(R.id.question_spinner);
        progressDialog = new ProgressDialog(this);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    adapter.add(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                String question_content = spinner.getSelectedItem().toString();

                if(content.isEmpty()){
                    editText.setError("回答内容を入力してください");
                }
                else{
                    progressDialog.setMessage("登録中です...");
                    progressDialog.setTitle("回答登録");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    Questions question = new Questions(content);
                    dao = new DAOQuestions(question_content);
                    dao.add(question);
                    Toast.makeText(AnswerActivity.this, "登録が完了しました！", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}