package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class Wind(
        @SerializedName("speed") val speed: Double,
        @SerializedName("deg") val degree: Double
)