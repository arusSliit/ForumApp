package com.example.mad.question;

import androidx.annotation.NonNull;

import com.example.mad.ui.home.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionDB {

    final private static DatabaseReference ref = FirebaseDatabase.getInstance("https://mad-sliit-92d31-default-rtdb.firebaseio.com/").getReference("questions");

    public static void newQuestion(String userID, String title, String question, String spaceID){
        Question questionObj = new Question();
        String id = ref.push().getKey();
        questionObj.setQuestion(question);
        questionObj.setQuestionID(id);
        questionObj.setSpaceID(spaceID);
        questionObj.setTitle(title);
        questionObj.setUserID(userID);
        questionObj.setTimestamp(System.currentTimeMillis());
        ref.child(id).setValue(questionObj);
    }

    public static void populateQuestions(ArrayList<Question> list,MyAdapter adapter){
        ref.orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question q = dataSnapshot.getValue(Question.class);
                    list.add(q);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static DatabaseReference getRef() {
        return ref;
    }
}
