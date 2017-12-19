package com.wipro.demo.weather.views;

import com.wipro.demo.weather.models.CityWeatherDetails;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */


public interface WeatherView {
    void showProgressDialog();

    void hideProgressDialog();

    void getWeatherDataSuccess(CityWeatherDetails weatherData);

    void getWeatherDataFailure(String appErrorMessage);
}
