package geekboy.weatherapp.data.remote

import android.arch.lifecycle.LiveData
import geekboy.weatherapp.data.model.api.weather.WeatherResponse
import geekboy.weatherapp.utils.retrofit.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiMethods {

    @GET("forecast")
    fun getWeatherWithGeoData(@Query("lat") lat: String,
                              @Query("lon") lon: String,
                              @Query("cnt")cnt:String="14",
                              @Query("units")unit:String="metric",
                              @Query("APPID")APP_ID:String=ApiEndPoint.API_KEY):
            LiveData<ApiResponse<WeatherResponse>>

    @GET("forecast")
    fun getWeatherWithCityData(@Query("id") lat: String,
                              @Query("cnt")cnt:String="14",
                              @Query("units")unit:String="metric",
                              @Query("APPID")APP_ID:String=ApiEndPoint.API_KEY):
            LiveData<ApiResponse<WeatherResponse>>

}