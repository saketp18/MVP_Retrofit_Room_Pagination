package com.lite.application.neworking.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.lite.application.neworking.persistence.model.RecipeDB;

public class ResponseModel extends ViewModel {

    private MutableLiveData<RecipeDB> recipes = new MutableLiveData<>();

    public MutableLiveData<RecipeDB> getRecipes() {
        return recipes;
    }

    public void setRecipes(RecipeDB recipes) {
        this.recipes.setValue(recipes);
    }
}
