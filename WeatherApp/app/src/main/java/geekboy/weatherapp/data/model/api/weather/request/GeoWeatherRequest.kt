package geekboy.weatherapp.data.model.api.weather.request

import geekboy.weatherapp.data.remote.ApiEndPoint

data class GeoWeatherRequest (var lat: String, var lon: String){
    companion object {
        const val cnt = "14"
        const val unit = "metric"
        val API_KEY = ApiEndPoint.API_KEY
    }
}