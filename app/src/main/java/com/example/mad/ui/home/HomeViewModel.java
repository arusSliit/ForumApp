package com.example.mad.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mad.question.Question;
import com.example.mad.question.QuestionDB;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeViewModel extends ViewModel {

    private ArrayList<Question> questions;
    private static MyAdapter adapter;

    public MyAdapter getAdapter() {
        return adapter;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public HomeViewModel() {
        questions = new ArrayList<Question>();
        adapter = new MyAdapter(questions);
        QuestionDB.populateQuestions(questions,adapter);
    }
    public static void updateViewCount(String questionID,int viewCount){
        HashMap<String,Object> map = new HashMap();
        map.put("viewCount",viewCount);
        QuestionDB.getRef().child(questionID).updateChildren(map);
        adapter.notifyDataSetChanged();
    }
}