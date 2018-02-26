package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class City(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("coord") val coordinates: Coordinates,
        @SerializedName("country") val country: String,
        @SerializedName("population") val population: Long

)