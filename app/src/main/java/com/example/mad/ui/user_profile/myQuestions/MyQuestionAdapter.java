package com.example.mad.ui.user_profile.myQuestions;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;
import com.example.mad.question.Question;
import com.example.mad.question.QuestionDB;
import com.example.mad.ui.question_and_answer.QuestionAndAnswer;
import com.example.mad.user_profile.UserProfileDB;

import java.util.ArrayList;

public class MyQuestionAdapter extends RecyclerView.Adapter<MyQuestionAdapter.MyQuestionHolder> {
    private ArrayList<Question> list;
    FragmentManager manager;
    public MyQuestionAdapter(ArrayList<Question> questions,FragmentManager manager){
        list = questions;
        this.manager = manager;
    }
    @NonNull
    @Override
    public MyQuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_question,parent,false);
        return new MyQuestionAdapter.MyQuestionHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyQuestionHolder holder, int position) {
        Question q = list.get(position);
        holder.title.setText(q.getTitle());
        MyQuestionAdapter adapter = this;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileDB.deleteQuestion(q.getQuestionID(),adapter);
            }
        });
        holder.navToQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), QuestionAndAnswer.class);
                intent.putExtra("id", q.getQuestionID());
                intent.putExtra("commentCount",0);
                intent.putExtra("question","Question");
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMyQuestion frag = new UpdateMyQuestion(q,adapter);
                frag.show(manager,"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyQuestionHolder extends RecyclerView.ViewHolder{

        TextView navToQuestion, title;
        ImageButton delete;

        public MyQuestionHolder(@NonNull View itemView) {

            super(itemView);
            navToQuestion = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.s_title);
            delete = itemView.findViewById(R.id.delete_my_question);
        }
    }
}