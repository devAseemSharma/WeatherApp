package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class WeatherList(
        @SerializedName("dt") val date: Long,
        @SerializedName("main") val mainTemperature: MainTemperature,
        @SerializedName("weather") val weather: List<Weather>,
        @SerializedName("clouds") val clouds: Clouds,
        @SerializedName("wind") val wind: Wind,
        @SerializedName("rain") val rain: Rain,
        @SerializedName("sys") val sys: Sys,
        @SerializedName("dt_txt") val dateText: String
)