package com.cleanup.todoc.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cleanup.todoc.DI.Di;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TodocRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class TodocViewModel extends AndroidViewModel {

    @Nullable
    private LiveData<List<Project>> mProjects;

    private TodocRepository todocRepository;
    private LiveData<List<Task>> tasksList;
    private LiveData<List<Project>> projectList;

    public TodocViewModel(@NonNull Application application) {

        super(application);
        todocRepository = Di.getTaskRepository(application);
        tasksList = todocRepository.getTasks();
        projectList = todocRepository.getProjects();
    }



    public LiveData<List<Task>> getTasksList() {
        return tasksList;
    }

    public LiveData<List<Project>> getProjectList() {
        return projectList;
    }

    public Completable deleteTask(Task task) {

        return todocRepository.deleteTasks(task);
    }

    public Completable addNewTask(Task tasks) {
        return todocRepository.insertTask(tasks);
    }

}
