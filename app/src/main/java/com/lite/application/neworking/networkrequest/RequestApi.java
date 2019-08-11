package com.lite.application.neworking.networkrequest;

import com.lite.application.neworking.networkrequest.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestApi {

    @GET("v2/everything")
    Call<Response> getResponse(@Query("q") String query,
                               @Query("apiKey") String key,
                               @Query("page") int page,
                               @Query("pageSize") int size);
}
