package com.example.mad.ui.comment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;
import com.example.mad.answer.Answer;
import com.example.mad.comment.Comment;
import com.example.mad.ui.answer.AnswerAdapter;
import com.example.mad.user.UserDB;
import com.example.mad.user.UserObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private ArrayList<Comment> list;

    public CommentAdapter(ArrayList<Comment> comments){
        list = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment,parent,false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment c = list.get(position);
        holder.comment.setText(c.getComment());
        UserDB.setNameAndPhoto(c.getUserID(),holder.profilePhoto,holder.userName,this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView comment,userName;
        ImageView profilePhoto;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.cmt);
            userName = itemView.findViewById(R.id.user_name_cmt);
            profilePhoto = itemView.findViewById(R.id.profile_pic_cmt);
        }
    }
}
