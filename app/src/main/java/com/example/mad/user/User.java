package com.example.mad.user;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class User {

    private static final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public static String getName(){
        return user.getDisplayName();
    }
    public static String getEmail(){
        return user.getEmail();
    }
    public static Uri getProfileImage(){
        return user.getPhotoUrl();
    }
    public static String getUserID(){
        return user.getUid();
    }

    public static void updateDisplayName(String name){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            UserDB.updateName(name);
                        }
                    }
                });
    }
    protected static void updateProfilePic(Uri uri){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            UserDB.updateProfileImg(uri.toString());
                        }
                    }
                });
    }
    public static void updateEmail(String email){
        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            UserDB.updateEmail(email);
                        }
                    }
                });
    }
    public static void updatePassword(String password){
        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        }
                    }
                });
    }
}
