package com.example.mad.ui.dashboard;

import android.app.Application;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mad.answer.Answer;
import com.example.mad.question.Question;
import com.example.mad.space.Space;
import com.example.mad.space.SpaceDB;
import com.example.mad.ui.user_profile.myAnswers.MyAnswerAdapter;
import com.example.mad.ui.user_profile.myQuestions.MyQuestionAdapter;
import com.example.mad.ui.user_profile.mySpaces.MySpaceAdapter;
import com.example.mad.user_profile.UserProfileDB;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {
    private ArrayList<Answer> answers;
    private ArrayList<Question> questions;
    private ArrayList<Space> spaces;
    private MyQuestionAdapter qAdapter;
    private MyAnswerAdapter aAdapter;
    private static MySpaceAdapter sAdapter;

    public MySpaceAdapter getsAdapter() {
        return sAdapter;
    }

    private FragmentManager manager;

    public MyQuestionAdapter getqAdapter() {
        return qAdapter;
    }

    public MyAnswerAdapter getaAdapter() {
        return aAdapter;
    }


    public DashboardViewModel(FragmentManager manager) {
        this.manager = manager;
        answers = new ArrayList<>();
        questions = new ArrayList<>();
        spaces = new ArrayList<>();
        qAdapter = new MyQuestionAdapter(questions,manager);
        aAdapter = new MyAnswerAdapter(answers,questions,manager);
        sAdapter = new MySpaceAdapter(spaces,manager);
        UserProfileDB.populateMyQuestions(questions,qAdapter);
        UserProfileDB.populateMyAnswers(answers,aAdapter);
        UserProfileDB.populateMySpaces(spaces,sAdapter);
    }
    public static void deleteSpace(String spaceID){
        SpaceDB.getRef().child(spaceID).removeValue();
        sAdapter.notifyDataSetChanged();
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }


}