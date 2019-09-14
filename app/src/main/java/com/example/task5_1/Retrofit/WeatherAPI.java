package com.example.task5_1.Retrofit;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherAPI {
    public static String KEY = "fa22e609007c1a7c2e37b38bef5473f2";
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static Retrofit retrofit = null;

    public interface ApiInterface {
        @GET("weather")
        Call<WeatherDay> getToday(
                @Query("lat") Double lat,
                @Query("lon") Double lon,
                @Query("units") String units,
                @Query("appid") String appid
        );

        @GET("forecast")
        Call<WeatherForecast> getForecast(
                @Query("lat") Double lat,
                @Query("lon") Double lon,
                @Query("units") String units,
                @Query("appid") String appid
        );
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}