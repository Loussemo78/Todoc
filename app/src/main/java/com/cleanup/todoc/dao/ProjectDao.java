package com.cleanup.todoc.dao;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

        @Query("SELECT * FROM Project WHERE Id = :projectId")
        LiveData<List<Project>> getItems(long projectId);

        @Insert
        void insertProject(Project... project);

        @Update
        int updateProject(Project project);

        @Query("SELECT * FROM Project")
        public LiveData<List<Project>> AllProjects();

        @Query("DELETE FROM Project WHERE id = :projectId")
        int deleteProject(long projectId);


}
