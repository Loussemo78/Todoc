package com.cleanup.todoc.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class TodocRepository {

    private TaskDao taskDao;
    private ProjectDao projectDao;
    private TodocDataBase tdb;


    public TodocRepository(Application application) {
        tdb = TodocDataBase.getDataBase(application);
        projectDao = tdb.projectDao();
        taskDao = tdb.taskDao();
        //projects.setValue(new ArrayList<Project>(Arrays.asList(Project.getAllProjects())));
    }

    public LiveData<List<Task>> getTasks() {
        return taskDao.AllTasks();
    }

    public Completable insertTask(final Task tasks) {

        return Completable.fromAction(new Action() {
            @Override
            public void run()
            {
                tdb.taskDao().insertTasks(tasks);
            }
        })
            //.doOnError(error -> System.err.println("The error message is: " + error.getMessage()));

        .subscribeOn(Schedulers.io());
        //.observeOn(AndroidSchedulers.mainThread());
    }


    public Completable deleteTasks(Task tasks) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                tdb.taskDao().deleteTasks(tasks);

            }
        })
                .subscribeOn(Schedulers.io());

    }

    public LiveData<List<Project>> getProjects() {
        return projectDao.AllProjects();
    }


}
