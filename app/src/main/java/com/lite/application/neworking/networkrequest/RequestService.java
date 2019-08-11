package com.lite.application.neworking.networkrequest;

import com.lite.application.neworking.utils.Utility;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestService {

    private static Retrofit retrofit = null;

    private RequestService(){

    }

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder().baseUrl(Utility.BASE_URL).client(client)
                            .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
