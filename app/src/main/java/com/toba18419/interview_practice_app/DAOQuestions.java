package com.toba18419.interview_practice_app;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOQuestions {
    private DatabaseReference databaseReference;
    public DAOQuestions(String question){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Questions/"+question);
    }
    public Task<Void> add(Questions que){
        return databaseReference.setValue(que);
    }
}
