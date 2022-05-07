package com.example.mad.ui.questions_in_space;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class QuestionsInSpaceViewModelFactory implements ViewModelProvider.Factory {

    private String spaceID;
    public QuestionsInSpaceViewModelFactory(String id){
        spaceID = id;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) new QuestionsInSpaceViewModel(spaceID);
    }
}
