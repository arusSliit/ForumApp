package com.example.mad.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mad.space.Space;
import com.example.mad.space.SpaceDB;

import java.util.ArrayList;

public class NotificationsViewModel extends ViewModel {

    private ArrayList<Space> spaces;
    private SpaceAdapter adapter;

    public SpaceAdapter getAdapter() {
        return adapter;
    }

    public NotificationsViewModel() {
        spaces = new ArrayList<Space>();
        adapter = new SpaceAdapter(spaces);
        SpaceDB.populateSpaces(spaces,adapter);
    }

}