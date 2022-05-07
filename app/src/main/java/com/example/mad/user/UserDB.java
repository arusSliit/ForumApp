package com.example.mad.user;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class UserDB {
    final private static DatabaseReference ref = FirebaseDatabase.getInstance("https://mad-sliit-92d31-default-rtdb.firebaseio.com/").getReference("users");

    public static void newUser(){
        UserObj user = new UserObj();
        user.setUserID(User.getUserID());
        user.setEmail(User.getEmail());
        user.setName(User.getName());
        user.setProfileImg("");
        ref.child(User.getUserID()).setValue(user);
    }
    public static void updateEmail(String email){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("email",email);
        ref.child(User.getUserID()).updateChildren(hashMap);
    }
    public static void updateName(String name){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name",name);
        ref.child(User.getUserID()).updateChildren(hashMap);
    }
    public static void updateProfileImg(String img){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("profileImg",img);
        ref.child(User.getUserID()).updateChildren(hashMap);
    }
    public static void setNameAndPhoto(String id, ImageView img, TextView txt,RecyclerView.Adapter adapter){
        ref.orderByChild("userID").equalTo(id).limitToFirst(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   UserObj u = dataSnapshot.getValue(UserObj.class);
                   Picasso.get().load(Uri.parse(u.getProfileImg())).into(img);
                   txt.setText(u.getName());
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
