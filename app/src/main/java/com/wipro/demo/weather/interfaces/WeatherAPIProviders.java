package com.wipro.demo.weather.interfaces;

import com.wipro.demo.common.network.NetworkManager;
import com.wipro.demo.weather.services.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

@Module
public class WeatherAPIProviders {
    @Provides
    @Singleton
    public WeatherAPI getWeatherAPI() {
        Retrofit retrofit = NetworkManager.getRetrofitInstance();
        return retrofit.create(WeatherAPI.class);
    }

    @Provides
    @Singleton
    public WeatherService providesService(WeatherAPI weatherAPI) {
        return new WeatherService(weatherAPI);
    }

}
