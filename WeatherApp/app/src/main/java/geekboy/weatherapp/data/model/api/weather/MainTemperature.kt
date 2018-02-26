package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class MainTemperature(
        @SerializedName("temp") val temp: String,
        @SerializedName("temp_min") val tempMin: String,
        @SerializedName("temp_max") val tempMax: String,
        @SerializedName("pressure") val pressure:String
)