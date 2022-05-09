package com.example.mad.ui.answer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;
import com.example.mad.answer.Answer;
import com.example.mad.ui.home.MyAdapter;
import com.example.mad.user.UserDB;
import com.example.mad.user.UserObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerHolder> {

    private ArrayList<Answer> answers;

    public AnswerAdapter(ArrayList<Answer> list){
        answers = list;
    }
    @NonNull
    @Override
    public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer,parent,false);
        return new AnswerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerHolder holder, int position) {
        Answer a = answers.get(position);
        holder.answer.setText(a.getAnswer());
        UserDB.setNameAndPhoto(a.getUserID(),holder.profileImg,holder.userName,this);
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public static class AnswerHolder extends RecyclerView.ViewHolder{

        TextView answer,userName;
        ImageView profileImg;

        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            answer = itemView.findViewById(R.id.ans);
            userName = itemView.findViewById(R.id.user_name_ans);
            profileImg = itemView.findViewById(R.id.profile_pic_ans);
        }
    }
}
