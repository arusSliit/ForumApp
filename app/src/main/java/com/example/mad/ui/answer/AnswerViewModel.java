package com.example.mad.ui.answer;


import androidx.lifecycle.ViewModel;

import com.example.mad.answer.Answer;
import com.example.mad.answer.AnswerDB;

import java.util.ArrayList;

public class AnswerViewModel extends ViewModel {
    private ArrayList<Answer> answers;
    private AnswerAdapter adapter;

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public AnswerAdapter getAdapter() {
        return adapter;
    }

    public AnswerViewModel(){
        answers = new ArrayList<Answer>();
        adapter = new AnswerAdapter(answers);
        AnswerDB.populateAnswers(answers,adapter);
    }


}
