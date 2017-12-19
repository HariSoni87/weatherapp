package com.wipro.demo.weather.services;

import com.wipro.demo.weather.models.CityWeatherDetails;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */


public interface WeatherServiceCallBack {
    void onSuccess(CityWeatherDetails weatherData);
    void onError(Throwable error);
}
