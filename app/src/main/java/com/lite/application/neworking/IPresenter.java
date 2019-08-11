package com.lite.application.neworking;

import com.lite.application.neworking.networkrequest.models.Article;
import java.util.List;

public interface IPresenter {

    public interface NetworkResponse{
        public void onSuccess(List<Article> response);

        public void onFailure();

        public void onCompleted();
    }
}
