package com.cleanup.todoc.dao;

import androidx.lifecycle.LiveData;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertTasks(Task... task);

    @Delete
    void deleteTasks(Task... task);

    @Query("SELECT * FROM Task")
    public LiveData<List<Task>> AllTasks();

    @Query("SELECT * FROM Task WHERE id = :taskId")
    LiveData<List<Task>> getTasks(long taskId);


}
