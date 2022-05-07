package com.example.mad.ui.user_profile.myAnswers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad.R;
import com.example.mad.answer.Answer;
import com.example.mad.question.QuestionDB;
import com.example.mad.user_profile.UserProfileDB;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UpdateMyAnswer extends BottomSheetDialogFragment {

    private Answer answerObj;
    private MyAnswerAdapter adapter;

    public UpdateMyAnswer(Answer answer,MyAnswerAdapter adapter) {
        this.answerObj = answer;
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
        View v = inflater.inflate(R.layout.update_my_answer , container , false);
        EditText answer = (EditText) v.findViewById(R.id.update_answer);
        answer.setText(answerObj.getAnswer());
        Button saveQuestion = v.findViewById(R.id.update_answer_btn);
        saveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileDB.updateAnswer(answer.getText().toString(), answerObj.getAnswerID(),adapter);
                dismiss();
            }
        });
        return v;
    }
}
