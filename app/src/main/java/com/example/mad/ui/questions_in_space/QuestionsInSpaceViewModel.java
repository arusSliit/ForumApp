package com.example.mad.ui.questions_in_space;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.question.Question;
import com.example.mad.space.SpaceDB;
import com.example.mad.ui.home.MyAdapter;

import java.util.ArrayList;

public class QuestionsInSpaceViewModel extends ViewModel {

    private ArrayList<Question> questions;
    private RecyclerView.Adapter adapter;
    private String spaceID;

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public QuestionsInSpaceViewModel(String id) {
        spaceID = id;
        questions = new ArrayList<>();
        adapter = new MyAdapter(questions);
        SpaceDB.populateQuestionsOfSpace(questions,adapter,spaceID);
    }
}
