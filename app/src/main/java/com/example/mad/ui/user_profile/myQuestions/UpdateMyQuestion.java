package com.example.mad.ui.user_profile.myQuestions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad.R;
import com.example.mad.question.Question;
import com.example.mad.question.QuestionDB;
import com.example.mad.user_profile.UserProfileDB;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UpdateMyQuestion extends BottomSheetDialogFragment {

    private Question questionObj;
    private MyQuestionAdapter adapter;

    public UpdateMyQuestion(Question question,MyQuestionAdapter adapter) {
        questionObj = question;
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
        View v = inflater.inflate(R.layout.update_my_question , container , false);
        EditText title = (EditText) v.findViewById(R.id.question_title);
        EditText question = (EditText) v.findViewById(R.id.question);
        title.setText(questionObj.getTitle());
        question.setText(questionObj.getQuestion());
        Button saveQuestion = v.findViewById(R.id.update_question);
        saveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileDB.updateQuestion(title.getText().toString(),question.getText().toString(),questionObj.getQuestionID(),adapter);
                dismiss();
            }
        });
        return v;
    }
}
