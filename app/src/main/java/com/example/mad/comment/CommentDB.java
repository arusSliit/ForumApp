package com.example.mad.comment;

import androidx.annotation.NonNull;

import com.example.mad.answer.Answer;
import com.example.mad.question.QuestionDB;
import com.example.mad.ui.answer.AnswerAdapter;
import com.example.mad.ui.answer.Answers;
import com.example.mad.ui.comment.CommentAdapter;
import com.example.mad.ui.question_and_answer.QuestionAndAnswer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentDB {  //comment DB function
    final private static DatabaseReference ref = FirebaseDatabase.getInstance("https://mad-sliit-92d31-default-rtdb.firebaseio.com/").getReference("comments");

    public static void newComment(String userID,String questionID,String comment,int commentCount){
        Comment commentObj = new Comment();
        String id = ref.push().getKey();
        commentObj.setComment(comment);
        commentObj.setCommentID(id);
        commentObj.setQuestionID(questionID);
        commentObj.setUserID(userID);
        ref.child(id).setValue(commentObj);
        updateCommentCount(questionID,commentCount+1);
    }
    public static void updateCommentCount(String questionID,int commentCount){
        HashMap<String,Object> map = new HashMap();
        map.put("commentCount",commentCount);
        QuestionDB.getRef().child(questionID).updateChildren(map);
    }
    public static void populateComments(ArrayList<Comment> comments, CommentAdapter adapter){
        ref.orderByChild("questionID").equalTo(QuestionAndAnswer.questionID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Comment c = dataSnapshot.getValue(Comment.class);
                    comments.add(c);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
