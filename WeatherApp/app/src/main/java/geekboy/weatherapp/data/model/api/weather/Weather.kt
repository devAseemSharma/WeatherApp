package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class Weather(
  @SerializedName("id") val id: Int,
  @SerializedName("main") val main: String,
  @SerializedName("description") val weatherDescription: String

)