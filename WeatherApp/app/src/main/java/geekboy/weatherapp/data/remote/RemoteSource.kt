package geekboy.weatherapp.data.remote

import android.arch.lifecycle.LiveData
import geekboy.weatherapp.data.model.api.weather.WeatherResponse
import geekboy.weatherapp.data.model.api.weather.request.CityWeatherRequest
import geekboy.weatherapp.data.model.api.weather.request.GeoWeatherRequest
import geekboy.weatherapp.utils.retrofit.ApiResponse

interface RemoteSource {
    fun getWeatherFromGeo(geoWeatherRequest: GeoWeatherRequest): LiveData<ApiResponse<WeatherResponse>>
    fun getWeatherFromCityId(cityWeatherRequest: CityWeatherRequest): LiveData<ApiResponse<WeatherResponse>>
}