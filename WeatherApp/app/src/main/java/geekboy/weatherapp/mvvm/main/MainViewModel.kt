package geekboy.weatherapp.mvvm.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.util.Log
import geekboy.weatherapp.data.AppDataManager
import geekboy.weatherapp.data.model.api.weather.WeatherResponse
import geekboy.weatherapp.data.model.api.weather.request.CityWeatherRequest
import geekboy.weatherapp.data.model.api.weather.request.GeoWeatherRequest
import javax.inject.Inject

class MainViewModel
@Inject
constructor
(val dataManager: AppDataManager, val context: Context) :
        AndroidViewModel(context as Application){

    var weatherResponse = MutableLiveData<WeatherResponse>()
    var cityDataWeatherResponse = MutableLiveData<WeatherResponse>()
    var errorObserver = MutableLiveData<String>()

    fun getWeatherFromGeo(geoWeatherRequest: GeoWeatherRequest){
        dataManager.getWeatherFromGeo(geoWeatherRequest).observeForever(Observer {
            if (it?.code==200){
                Log.d("Result","Success")
                weatherResponse.postValue(it.body)
            }else{
                Log.d("Result","Error")
                Log.d("Result","Error${it?.response?.raw()?.request()?.url()}")
                errorObserver.postValue("Error Occurred, try again")
            }
        })
    }

    fun getWeatherFromCity(cityWeatherRequest: CityWeatherRequest){
        dataManager.getWeatherFromCityId(cityWeatherRequest).observeForever(Observer {
            if (it?.code==200){
                Log.d("Result","Success")
                cityDataWeatherResponse.postValue(it.body)
            }else{
                Log.d("Result","Error")
                Log.d("Result","Error${it?.response?.raw()?.request()?.url()}")
                errorObserver.postValue("Error Occurred, try again")
            }
        })
    }
}