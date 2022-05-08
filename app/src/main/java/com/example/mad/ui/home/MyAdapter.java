package com.example.mad.ui.home;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;
import com.example.mad.question.Question;
import com.example.mad.question.QuestionDB;
import com.example.mad.ui.question_and_answer.QuestionAndAnswer;
import com.example.mad.user.UserDB;
import com.example.mad.user.UserObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Question> list;

    public MyAdapter(ArrayList<Question> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question,parent,false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        Question q = list.get(position);
        UserDB.setNameAndPhoto(q.getUserID(),holder.userImage,holder.userName,this);
        holder.questionTitle.setText(q.getTitle());
        holder.question.setText(q.getQuestion());
        holder.comment.setText(Integer.toString(q.getCommentCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),QuestionAndAnswer.class);
                intent.putExtra("id", q.getQuestionID());
                intent.putExtra("commentCount",q.getCommentCount());
                intent.putExtra("question",q.getQuestion());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView userName, questionTitle,comment,viewCount,question;
        ImageView userImage;
        ImageButton like;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTitle = itemView.findViewById(R.id.question_card_title);
            userName = itemView.findViewById(R.id.question_card_user_name);
            userImage = itemView.findViewById(R.id.profile_pic_qsn);
            comment = itemView.findViewById(R.id.comment);
            viewCount = itemView.findViewById(R.id.viewCount);
            like = itemView.findViewById(R.id.like);
            question = itemView.findViewById(R.id.question);
        }
    }

}
