package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class Coordinates(
        @SerializedName("lat")val lat: Double,
        @SerializedName("lon")val lon: Double
)