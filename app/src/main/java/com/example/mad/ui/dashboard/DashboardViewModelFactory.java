package com.example.mad.ui.dashboard;

import android.app.Application;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DashboardViewModelFactory implements ViewModelProvider.Factory {
    private FragmentManager manager;


    public DashboardViewModelFactory(FragmentManager fm) {
        manager = fm;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DashboardViewModel(manager);
    }
}
