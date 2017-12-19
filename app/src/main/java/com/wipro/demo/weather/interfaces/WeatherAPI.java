package com.wipro.demo.weather.interfaces;

import com.wipro.demo.weather.models.CityWeatherDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

public interface WeatherAPI {
    @GET("forecast")
    Observable<CityWeatherDetails> getCityWeather(@Query("q") String cityNameCode, @Query("appid") String appId);
}
