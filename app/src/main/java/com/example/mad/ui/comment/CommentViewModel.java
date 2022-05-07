package com.example.mad.ui.comment;

import androidx.lifecycle.ViewModel;
import com.example.mad.comment.Comment;
import com.example.mad.comment.CommentDB;

import java.util.ArrayList;

public class CommentViewModel extends ViewModel {

    private ArrayList<Comment> comments;
    private CommentAdapter adapter;

    public ArrayList<Comment> getAnswers() {
        return comments;
    }

    public CommentAdapter getAdapter() {
        return adapter;
    }

    public CommentViewModel(){
        comments = new ArrayList<Comment>();
        adapter = new CommentAdapter(comments);
        CommentDB.populateComments(comments,adapter);
    }
}
