package com.lite.application.neworking.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.lite.application.neworking.persistence.model.RecipeDB;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    void insertRecipes(RecipeDB recipe);

    @Query("SELECT * FROM RecipeDB")
    LiveData<List<RecipeDB>> getRecipe();
}
