package com.example.mad.ui.questions_in_space;

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

public class NewQuestionInSpace extends BottomSheetDialogFragment {

    public NewQuestionInSpace() {
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
        View v = inflater.inflate(R.layout.new_question_bottom_sheet , container , false);
        EditText title = (EditText) v.findViewById(R.id.question_title);
        EditText question = (EditText) v.findViewById(R.id.question);
        Button saveQuestion = v.findViewById(R.id.save_question);
        saveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionDB.newQuestion(User.getUserID(),title.getText().toString(),question.getText().toString(),QuestionsInSpace.spaceID);
                dismiss();
            }
        });
        return v;
    }
}
