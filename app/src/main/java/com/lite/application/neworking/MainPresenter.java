package com.lite.application.neworking;

import android.content.Context;
import com.lite.application.neworking.networkrequest.RequestApi;
import com.lite.application.neworking.networkrequest.RequestService;
import com.lite.application.neworking.networkrequest.models.Response;
import com.lite.application.neworking.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;

public class MainPresenter {

    IPresenter.NetworkResponse iPresenter;

    public MainPresenter(Context context) {
        iPresenter = (IPresenter.NetworkResponse) context;
    }

    public void fetchNews(String query, int page, int pagesize) {
        if (page < Utility.PAGE_LIMIT) {
            RequestApi client = RequestService.getRetrofit().create(RequestApi.class);
            Call<Response> call = client.getResponse(query, Utility.API_KEY, page, pagesize);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.isSuccessful() && response.body().getStatus().equals("ok")) {
                        iPresenter.onSuccess(response.body().getArticles());
                    }else{
                        iPresenter.onCompleted();
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    iPresenter.onFailure();
                }
            });
        }else{
            iPresenter.onCompleted();
        }
    }
}
