package com.example.mad.ui.user_profile.myAnswers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;
import com.example.mad.answer.Answer;
import com.example.mad.question.Question;
import com.example.mad.ui.answer.AnswerAdapter;
import com.example.mad.ui.question_and_answer.QuestionAndAnswer;
import com.example.mad.user.UserDB;
import com.example.mad.user.UserObj;
import com.example.mad.user_profile.UserProfileDB;

import java.util.ArrayList;

public class MyAnswerAdapter extends RecyclerView.Adapter<MyAnswerAdapter.MyAnswerHolder> {
    ArrayList<Answer> list;
    ArrayList<Question> qlist;
    FragmentManager manager;
    public MyAnswerAdapter(ArrayList<Answer> answers,ArrayList<Question> questions,FragmentManager manager){
        list = answers;
        qlist = questions;
        this.manager = manager;
    }
    @NonNull
    @Override
    public MyAnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_answer,parent,false);
        return new MyAnswerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAnswerHolder holder, int position) {
        Answer a = list.get(position);
        Question q;
        holder.answer.setText(a.getAnswer());
        MyAnswerAdapter adapter = this;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileDB.deleteAnswer(a.getAnswerID(),adapter);
            }
        });
        holder.navigateToQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), QuestionAndAnswer.class);
                intent.putExtra("id", a.getQuestionID());
                intent.putExtra("question","Question");
                intent.putExtra("commentCount",0);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMyAnswer frag = new UpdateMyAnswer(a,adapter);
                frag.show(manager,"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyAnswerHolder extends RecyclerView.ViewHolder{

        TextView answer,navigateToQuestion;
        ImageButton delete;

        public MyAnswerHolder(@NonNull View itemView) {
            super(itemView);
            answer = itemView.findViewById(R.id.my_answer);
            navigateToQuestion = itemView.findViewById(R.id.nav_to_qsn_from_ans);
            delete = itemView.findViewById(R.id.delete_my_answer);
        }
    }
}
