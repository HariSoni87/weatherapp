
package com.wipro.demo.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

public class CityWeatherDetails {

    @SerializedName("city")
    @Expose
    public CityDetails cityDetails;

    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public Double message;
    @SerializedName("cnt")
    @Expose
    public Integer cnt;
    @SerializedName("list")
    @Expose
    public java.util.List<WeatherList> weatherList = null;

}
