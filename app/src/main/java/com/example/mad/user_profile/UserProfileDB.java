package com.example.mad.user_profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mad.answer.Answer;
import com.example.mad.answer.AnswerDB;
import com.example.mad.question.Question;
import com.example.mad.question.QuestionDB;
import com.example.mad.space.Space;
import com.example.mad.space.SpaceDB;
import com.example.mad.ui.dashboard.DashboardViewModel;
import com.example.mad.ui.user_profile.myAnswers.MyAnswerAdapter;
import com.example.mad.ui.user_profile.myQuestions.MyQuestionAdapter;
import com.example.mad.ui.user_profile.mySpaces.MySpaceAdapter;
import com.example.mad.user.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class UserProfileDB extends User {

    private static final FirebaseStorage storage = FirebaseStorage.getInstance();
    private static final StorageReference storageRef = storage.getReference();

    public static void populateMyAnswers(ArrayList<Answer> myAnswers, MyAnswerAdapter adapter){
        AnswerDB.getRef().orderByChild("userID").equalTo(User.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Answer a = dataSnapshot.getValue(Answer.class);
                    myAnswers.add(a);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static void populateMyQuestions(ArrayList<Question> myQuestions, MyQuestionAdapter adapter) {
        QuestionDB.getRef().orderByChild("userID").equalTo(User.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question q = dataSnapshot.getValue(Question.class);
                    myQuestions.add(q);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static void populateMySpaces(ArrayList<Space> mySpaces, MySpaceAdapter adapter){
        SpaceDB.getRef().orderByChild("userID").equalTo(User.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Space s = dataSnapshot.getValue(Space.class);
                    mySpaces.add(s);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static void updateQuestion(String title,String question,String questionID,MyQuestionAdapter adapter){
        HashMap<String,Object> map = new HashMap();
        map.put("title",title);
        map.put("question",question);
        QuestionDB.getRef().child(questionID).updateChildren(map);
        adapter.notifyDataSetChanged();
    }
    public static void deleteQuestion(String questionID,MyQuestionAdapter adapter){
        QuestionDB.getRef().child(questionID).removeValue();
        adapter.notifyDataSetChanged();
    }
    public static void deleteAnswer(String answerID,MyAnswerAdapter adapter){
        AnswerDB.getRef().child(answerID).removeValue();
        adapter.notifyDataSetChanged();
    }


    public static void updateAnswer(String answer,String answerID,MyAnswerAdapter adapter){
        HashMap<String,Object> map = new HashMap<>();
        map.put("answer",answer);
        AnswerDB.getRef().child(answerID).updateChildren(map);
        adapter.notifyDataSetChanged();

       }
    public static void updateSpace(String name,String description,String spaceID,MySpaceAdapter adapter){
        HashMap<String,Object> map = new HashMap();
        map.put("name",name);
        map.put("description",description);
        SpaceDB.getRef().child(spaceID).updateChildren(map);
        adapter.notifyDataSetChanged();
    }

       public static void uploadProfilePic(Uri filePath, ProgressDialog pd,Context context){
           if(filePath != null) {
               pd.show();

               StorageReference childRef = storageRef.child(User.getUserID()+"image.jpg");

               //uploading the image
               UploadTask uploadTask = childRef.putFile(filePath);

               uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       pd.dismiss();
                       childRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                           @Override
                           public void onSuccess(Uri uri) {
                               User.updateProfilePic(uri);
                               Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show();
                           }
                       });
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       pd.dismiss();
                       Toast.makeText(context, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                   }
               });
           }
           else {
               Toast.makeText(context, "Select an image", Toast.LENGTH_SHORT).show();
           }
       }

    }

