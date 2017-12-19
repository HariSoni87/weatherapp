package com.wipro.demo.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wipro.demo.weather.R;
import com.wipro.demo.weather.models.Teamperature;
import com.wipro.demo.weather.models.Weather;
import com.wipro.demo.weather.models.WeatherList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hari.soni@wipro.com on 17/12/17.
 */

public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.ViewHolder> {
    private static final String TAG = CityWeatherAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<WeatherList> cityWeatherList;

    public CityWeatherAdapter(Context context, ArrayList<WeatherList> cityWeatherList) {
        this.cityWeatherList = cityWeatherList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherList data = cityWeatherList.get(position);

        Teamperature temperature = data.teamperature;
        holder.minTemp.setText(String.format(context.getString(R.string.minTemp), temperature.tempMin));
        holder.maxTemp.setText(String.format(context.getString(R.string.maxTemp), temperature.tempMax));
        holder.tvHumidity.setText( String.valueOf(temperature.humidity));

        try {
            ArrayList<Weather> weather = (ArrayList<Weather>) data.weather;
            holder.desc.setText(String.valueOf(weather.get(0).description));
        } catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        if (cityWeatherList != null) {
            return cityWeatherList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.min_temp)
        TextView minTemp;
        @BindView(R.id.max_temp)
        TextView maxTemp;
        @BindView(R.id.humidity)
        TextView tvHumidity;
        @BindView(R.id.description)
        TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
