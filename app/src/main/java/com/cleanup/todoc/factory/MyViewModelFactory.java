package com.cleanup.todoc.factory;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.cleanup.todoc.ui.TodocViewModel;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;


    public MyViewModelFactory(Application application) {
        mApplication = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TodocViewModel(mApplication);
    }
}
