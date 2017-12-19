package com.wipro.demo.weather.dependencyinjectors;

import com.wipro.demo.common.network.NetworkManager;
import com.wipro.demo.weather.activity.WeatherActivity;
import com.wipro.demo.weather.interfaces.WeatherAPIProviders;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

@Singleton
@Component(modules = {NetworkManager.class, WeatherAPIProviders.class})

public interface WeatherDependencyInjector {
    void inject(WeatherActivity activity);
}
