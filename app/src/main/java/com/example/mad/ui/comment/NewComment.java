package com.example.mad.ui.comment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad.R;
import com.example.mad.answer.AnswerDB;
import com.example.mad.comment.CommentDB;
import com.example.mad.ui.question_and_answer.QuestionAndAnswer;
import com.example.mad.user.User;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NewComment extends BottomSheetDialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.new_comment , container , false);
        EditText comment = (EditText) v.findViewById(R.id.new_comment);
        Button saveComment = v.findViewById(R.id.new_comment_btn);
        saveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentDB.newComment(User.getUserID(),QuestionAndAnswer.questionID,comment.getText().toString(),QuestionAndAnswer.commentCount);
                dismiss();
            }
        });
        return v;
    }
}
