package com.example.task5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.task5_1.Retrofit.Coord;
import com.example.task5_1.Retrofit.WeatherAPI;
import com.example.task5_1.Retrofit.WeatherDay;
import com.example.task5_1.Retrofit.WeatherForecast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weather extends AppCompatActivity {
    String TAG = "WEATHER";
    TextView tvTemp;
    ImageView tvImage;
    LinearLayout llForecast;
    WeatherAPI.ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvImage = (ImageView) findViewById(R.id.ivImage);
        llForecast = (LinearLayout) findViewById(R.id.llForecast);

        api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);
    }

    public void getWeather(View v) {
       // coords=new Coord();
       //Double lat=coords.getLat();
       //Double lat = 56.128;
       // Double lng = 11.409;
        Double lat = 67.9;
        Double lng = 23.;
        String units = "metric";
        String key = WeatherAPI.KEY;

        Log.d(TAG, "OK");

        // get weather for today
        Call<WeatherDay> callToday = api.getToday(lat, lng, units, key);
        callToday.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                Log.e(TAG, "onResponse");
                WeatherDay data = response.body();
                //Log.d(TAG,response.toString());

                if (response.isSuccessful()) {
                    tvTemp.setText(data.getCity() + " " + data.getTempWithDegree());
                    Glide.with(Weather.this).load(data.getIconUrl()).into(tvImage);
                }
            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });

        // get weather forecast
        Call<WeatherForecast> callForecast = api.getForecast(lat, lng, units, key);
        callForecast.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                Log.e(TAG, "onResponse");
                WeatherForecast data = response.body();
                //Log.d(TAG,response.toString());

                if (response.isSuccessful()) {
                    SimpleDateFormat formatDayOfWeek = new SimpleDateFormat("E");
                    LinearLayout.LayoutParams paramsTextView = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout.LayoutParams paramsImageView = new LinearLayout.LayoutParams(convertDPtoPX(40, Weather.this),
                            convertDPtoPX(40, Weather.this));

                    int marginRight = convertDPtoPX(15, Weather.this);
                    LinearLayout.LayoutParams paramsLinearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    paramsLinearLayout.setMargins(0, 0, marginRight, 0);

                    llForecast.removeAllViews();

                    for (WeatherDay day : data.getItems()) {
                        if (day.getDate().get(Calendar.HOUR_OF_DAY) == 15) {
                            String date = String.format("%d.%d.%d %d:%d",
                                    day.getDate().get(Calendar.DAY_OF_MONTH),
                                    day.getDate().get(Calendar.WEEK_OF_MONTH),
                                    day.getDate().get(Calendar.YEAR),
                                    day.getDate().get(Calendar.HOUR_OF_DAY),
                                    day.getDate().get(Calendar.MINUTE)
                            );
                            Log.d(TAG, date);
                            Log.d(TAG, day.getTempInteger());
                            Log.d(TAG, "---");

                            // child view wrapper
                            LinearLayout childLayout = new LinearLayout(Weather.this);
                            childLayout.setLayoutParams(paramsLinearLayout);
                            childLayout.setOrientation(LinearLayout.VERTICAL);

                            // show day of week
                            TextView tvDay = new TextView(Weather.this);
                            String dayOfWeek = formatDayOfWeek.format(day.getDate().getTime());
                            tvDay.setText(dayOfWeek);
                            tvDay.setLayoutParams(paramsTextView);
                            childLayout.addView(tvDay);

                            // show image
                            ImageView ivIcon = new ImageView(Weather.this);
                            ivIcon.setLayoutParams(paramsImageView);
                            Glide.with(Weather.this).load(day.getIconUrl()).into(ivIcon);
                            childLayout.addView(ivIcon);

                            // show temp
                            TextView tvTemp = new TextView(Weather.this);
                            tvTemp.setText(day.getTempWithDegree());
                            tvTemp.setLayoutParams(paramsTextView);
                            childLayout.addView(tvTemp);

                            llForecast.addView(childLayout);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });

    }

    public int convertDPtoPX(int dp, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        int px = (int)(dp * density);
        return px;
    }

}