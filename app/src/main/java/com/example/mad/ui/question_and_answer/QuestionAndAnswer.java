package com.example.mad.ui.question_and_answer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mad.R;
import com.example.mad.ui.answer.Answers;
import com.example.mad.ui.answer.NewAnswer;
import com.example.mad.ui.comment.Comments;
import com.example.mad.ui.comment.NewComment;

public class QuestionAndAnswer extends AppCompatActivity {

    public static String questionID;
    public static int commentCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_answer);
        Intent intent = getIntent();
        String message = intent.getStringExtra("id");
        commentCount = intent.getIntExtra("commentCount",0);
        questionID = message;
        String q = intent.getStringExtra("question");
        TextView question = findViewById(R.id.question);
        question.setText(q);
        Fragment answers = Answers.newInstance("","",message);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.comment_answer_placeholder, answers)
                .commit();
        TextView button = findViewById(R.id.comment);
        Button newComment = findViewById(R.id.new_comment_btn);
        Button newAnswer = findViewById(R.id.new_answer_bottom_sheet);
        newComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewComment frag = new NewComment();
                frag.show(getSupportFragmentManager(),"");
            }
        });
        newAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewAnswer frag = new NewAnswer();
                frag.show(getSupportFragmentManager(),"");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.comment_answer_placeholder, Comments.newInstance("",""))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}