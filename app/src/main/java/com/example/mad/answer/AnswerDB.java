package com.example.mad.answer;

import androidx.annotation.NonNull;

import com.example.mad.question.Question;
import com.example.mad.ui.answer.AnswerAdapter;
import com.example.mad.ui.answer.Answers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnswerDB {
    final private static DatabaseReference ref = FirebaseDatabase.getInstance("https://mad-sliit-92d31-default-rtdb.firebaseio.com/").getReference("answers");

    public static void newAnswer(String questionID,String userID,String answer){
        Answer answerObj = new Answer();
        String id = ref.push().getKey();
        answerObj.setAnswer(answer);
        answerObj.setAnswerID(id);
        answerObj.setQuestionID(questionID);
        answerObj.setUserID(userID);
        answerObj.setUpVotes(0);
        ref.child(id).setValue(answerObj);
    }
    public static void populateAnswers(ArrayList<Answer> answers, AnswerAdapter adapter){
        ref.orderByChild("questionID").equalTo(Answers.questionID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Answer a = dataSnapshot.getValue(Answer.class);
                    answers.add(a);
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
