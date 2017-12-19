package com.wipro.demo.common.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wipro.demo.weather.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/** Singleton class used to crate retrofit instance.
 * Created by hari.soni@wipro,com on 16/12/17.
 */
@Module
public class NetworkManager {

    private Gson gson;
    private static Retrofit retrofit;


    private NetworkManager() throws RuntimeException {
        if(retrofit != null){
            throw new RuntimeException("Instance is already created. Call getRetrofitInstance()");
        }
        gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.BASEURL).
                addConverterFactory(GsonConverterFactory.create(gson)).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                client(httpClient.build()).
                build();
    }

    /**
     *
     * @return Retrofit instance for application
     */
    @Provides
    @Singleton
    public static synchronized Retrofit getRetrofitInstance(){
        if(retrofit == null){
                new NetworkManager();
        }
        return retrofit;
    }
}
