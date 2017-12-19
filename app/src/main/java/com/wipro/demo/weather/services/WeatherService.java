package com.wipro.demo.weather.services;

import com.wipro.demo.weather.BuildConfig;
import com.wipro.demo.weather.interfaces.WeatherAPI;
import com.wipro.demo.weather.models.CityWeatherDetails;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

@Module
public class WeatherService {

    WeatherAPI weatherAPI;
    String appId = BuildConfig.APP_ID;

    @Inject
    public WeatherService(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    @Provides
    public Observer<CityWeatherDetails> getCityWeatherData(String cityNameCode, final WeatherServiceCallBack callBackListner) {

         return weatherAPI.getCityWeather(cityNameCode, appId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<CityWeatherDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CityWeatherDetails weatherData) {
                        if(weatherData != null) {
                            callBackListner.onSuccess(weatherData);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBackListner.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
