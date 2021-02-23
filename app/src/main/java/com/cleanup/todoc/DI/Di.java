package com.cleanup.todoc.DI;

import android.app.Application;

import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.repositories.TodocRepository;

public class Di {
    private static boolean instantiateDbInMemory = false;
    private static TodocDataBase db = null;
    private static TodocRepository repository = null;

    public static TodocRepository getTaskRepository(Application application) {
        if (repository == null) {
            if (instantiateDbInMemory) {
                db = TodocDataBase.getNewDatabaseInMemory(application);
            } else {
                db = TodocDataBase.getDataBase(application);
            }
            repository = new TodocRepository(application);
        }
        return repository;
    }

    public static void setInstantiateDbInMemory(boolean val) {
        instantiateDbInMemory = val;
    }
}
