
package com.wipro.demo.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

public class WeatherList {

    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("main")
    @Expose
    public Teamperature teamperature;
    @SerializedName("weather")
    @Expose
    public java.util.List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("dt_Txt")
    @Expose
    public String dtTxt;

}
