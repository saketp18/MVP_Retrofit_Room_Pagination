package com.lite.application.neworking.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.lite.application.neworking.persistence.model.RecipeDB;

@Database(entities = {RecipeDB.class}, exportSchema =  false, version = 1)
public abstract class RecipeRepo extends RoomDatabase{

    public abstract RecipeDAO doDaoModelAccess();

}

