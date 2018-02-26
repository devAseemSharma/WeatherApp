package geekboy.weatherapp.data

import android.arch.lifecycle.LiveData
import android.content.Context
import geekboy.weatherapp.data.local.pref.AppPreferenceManager
import geekboy.weatherapp.data.model.api.weather.WeatherResponse
import geekboy.weatherapp.data.model.api.weather.request.CityWeatherRequest
import geekboy.weatherapp.data.model.api.weather.request.GeoWeatherRequest
import geekboy.weatherapp.data.remote.RemoteDataManager
import geekboy.weatherapp.utils.retrofit.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager : DataManager {
    private var remoteDataManager: RemoteDataManager

    private var appPreferenceManager: AppPreferenceManager

    private var context: Context

    @Inject
    constructor(context: Context, remoteDataManager: RemoteDataManager, preferenceManager: AppPreferenceManager) {
        this@AppDataManager.remoteDataManager = remoteDataManager
        this@AppDataManager.context = context
        this@AppDataManager.appPreferenceManager = preferenceManager
    }
    override fun getWeatherFromGeo(geoWeatherRequest: GeoWeatherRequest): LiveData<ApiResponse<WeatherResponse>> =
            remoteDataManager.getWeatherFromGeo(geoWeatherRequest)

    override fun getWeatherFromCityId(cityWeatherRequest: CityWeatherRequest): LiveData<ApiResponse<WeatherResponse>> {
        return remoteDataManager.getWeatherFromCityId(cityWeatherRequest)
    }


    override fun getCurrentUserLoggedInMode(): Int =
            appPreferenceManager.getCurrentUserLoggedInMode()

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        appPreferenceManager.setCurrentUserLoggedInMode(mode)
    }


    override fun getCurrentUserId(): String = appPreferenceManager.getCurrentUserId()

    override fun setCurrentUserId(userID: String) {
        appPreferenceManager.setCurrentUserId(userID)
    }

    override fun clearUserData() {
        appPreferenceManager.clearUserData()
    }


}