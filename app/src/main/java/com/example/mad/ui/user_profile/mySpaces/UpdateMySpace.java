package com.example.mad.ui.user_profile.mySpaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad.R;
import com.example.mad.question.QuestionDB;
import com.example.mad.user_profile.UserProfileDB;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UpdateMySpace extends BottomSheetDialogFragment {

    private String spaceID;
    private MySpaceAdapter adapter;

    public UpdateMySpace(String id,MySpaceAdapter adapter) {
        spaceID = id;
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.update_my_space , container , false);
        EditText title = (EditText) v.findViewById(R.id.space_title);
        EditText name = (EditText) v.findViewById(R.id.space_name);
        Button saveSpace = v.findViewById(R.id.update_space);
        saveSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileDB.updateSpace(title.getText().toString(),name.getText().toString(),spaceID,adapter);
                dismiss();
            }
        });
        return v;
    }
}
