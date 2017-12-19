package com.wipro.demo.weather.presenter;


import com.wipro.demo.weather.models.CityWeatherDetails;
import com.wipro.demo.weather.services.WeatherService;
import com.wipro.demo.weather.services.WeatherServiceCallBack;
import com.wipro.demo.weather.views.WeatherView;

import javax.inject.Inject;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 * Model
 */


public class WeatherPresenter {
    private final WeatherService service;
    private final WeatherView view;

    @Inject
    public WeatherPresenter(WeatherService service, WeatherView view) {
        this.service = service;
        this.view = view;
    }

    public void getWeatherData(String cityName){
        view.showProgressDialog();
        service.getCityWeatherData(cityName, new WeatherServiceCallBack() {
            @Override
            public void onSuccess(CityWeatherDetails weatherData) {
                view.hideProgressDialog();
                view.getWeatherDataSuccess(weatherData);
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgressDialog();
                view.getWeatherDataFailure(error.getMessage());
            }
        });

    }

}
