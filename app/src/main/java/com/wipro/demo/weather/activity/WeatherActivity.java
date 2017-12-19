package com.wipro.demo.weather.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wipro.demo.common.activity.BaseActivity;
import com.wipro.demo.weather.R;
import com.wipro.demo.weather.adapter.CityWeatherAdapter;
import com.wipro.demo.weather.dependencyinjectors.DaggerWeatherDependencyInjector;
import com.wipro.demo.weather.dependencyinjectors.WeatherDependencyInjector;
import com.wipro.demo.weather.interfaces.WeatherAPIProviders;
import com.wipro.demo.weather.models.CityWeatherDetails;
import com.wipro.demo.weather.models.WeatherList;
import com.wipro.demo.weather.presenter.WeatherPresenter;
import com.wipro.demo.weather.services.WeatherService;
import com.wipro.demo.weather.views.WeatherView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

public class WeatherActivity extends BaseActivity implements WeatherView {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView cityName;

    @BindView(R.id.progressBar)
    ProgressBar weatherProgressBar;

    @BindView(R.id.recycleView)
    RecyclerView weatherRecycleView;


    public WeatherPresenter weatherPresenter;


    WeatherDependencyInjector weatherDependencyInjector;

    @Inject
    WeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        ButterKnife.bind(this);
        weatherDependencyInjector = DaggerWeatherDependencyInjector.create();
        weatherDependencyInjector.inject(this);
        initializeViews();

        weatherPresenter = new WeatherPresenter(weatherService,this);

    }

    private void initializeViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        weatherRecycleView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        weatherRecycleView.addItemDecoration(divider);
    }

    @OnClick(R.id.button)
    public void getWatherData(){
        String cityNameText = cityName.getText().toString();
        if(cityNameText.length() > 0){
            weatherPresenter.getWeatherData(cityNameText);
        }else{
            Toast.makeText(WeatherActivity.this,
                    "Please enter city name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressDialog() {
        weatherProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressDialog() {
        weatherProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void getWeatherDataSuccess(CityWeatherDetails weatherData) {
        CityWeatherAdapter adapter = new CityWeatherAdapter(WeatherActivity.this,
                (ArrayList<WeatherList>) weatherData.weatherList);
        weatherRecycleView.setAdapter(adapter);
    }

    @Override
    public void getWeatherDataFailure(String appErrorMessage) {
        Toast.makeText(WeatherActivity.this,
                appErrorMessage, Toast.LENGTH_SHORT).show();
    }
}
