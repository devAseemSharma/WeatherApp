package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
        @SerializedName("cod") val code: String,
        @SerializedName("message") val message: Double,
        @SerializedName("list") val weatherList: List<WeatherList>,
        @SerializedName("city") val city: City
)