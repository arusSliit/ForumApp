package com.example.mad.space;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.question.Question;
import com.example.mad.question.QuestionDB;
import com.example.mad.ui.notifications.SpaceAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SpaceDB {

    //default constructor created
    public SpaceDB() {
    }

    final private static DatabaseReference ref = FirebaseDatabase.getInstance("https://mad-sliit-92d31-default-rtdb.firebaseio.com/").getReference("spaces");

    public static DatabaseReference getRef() {
        return ref;
    }
    //overloaded constructor created
    public static void newSpace(String userID, String name, String description){
        Space spaceObj = new Space();
        String id = ref.push().getKey();
        spaceObj.setSpaceID(id);
        spaceObj.setDescription(description);
        spaceObj.setName(name);
        spaceObj.setUserID(userID);
        ref.child(id).setValue(spaceObj);
    }

    public static void populateSpaces(ArrayList<Space> spaces, SpaceAdapter adapter){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Space s = dataSnapshot.getValue(Space.class);
                    spaces.add(s);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //getting the value from the db annd add to the profile
    public static void populateQuestionsOfSpace(ArrayList<Question> questions, RecyclerView.Adapter adapter, String spaceID){
        QuestionDB.getRef().orderByChild("spaceID").equalTo(spaceID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question q = dataSnapshot.getValue(Question.class);
                    questions.add(q);
                }
                System.out.println(questions.size());
                adapter.notifyDataSetChanged();
            }
            //default method
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
