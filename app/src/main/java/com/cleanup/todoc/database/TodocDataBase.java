package com.cleanup.todoc.database;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;

import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.Executors;


@Database(entities = {Task.class, Project.class}, version = 1)
public abstract class TodocDataBase extends RoomDatabase {

    public abstract TaskDao taskDao();

    public abstract ProjectDao projectDao();

    private static TodocDataBase INSTANCE;


    public static TodocDataBase getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDataBase.class, "TodocDataBase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    //TEST
    public static TodocDataBase getNewDatabaseInMemory(final Context context) {
        INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                TodocDataBase.class)
                .allowMainThreadQueries()
                .addCallback(prepopulateDatabase())
                .build();
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Project[] projects = Project.getAllProjects();
                for (Project project : projects) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", project.getId());
                    contentValues.put("name", project.getName());
                    contentValues.put("color", project.getColor());
                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };
    }

}