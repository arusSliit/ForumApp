package com.example.mad.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad.R;
import com.example.mad.question.QuestionDB;
import com.example.mad.space.SpaceDB;
import com.example.mad.user.User;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NewSpace extends BottomSheetDialogFragment {

    public NewSpace() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.new_space , container , false);
        EditText title = (EditText) v.findViewById(R.id.space_title);
        EditText description = (EditText) v.findViewById(R.id.space_description);
        Button saveSpace = v.findViewById(R.id.save_space);
        saveSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpaceDB.newSpace(User.getUserID(),title.getText().toString(),description.getText().toString());
                dismiss();
            }
        });
        return v;
    }
}
