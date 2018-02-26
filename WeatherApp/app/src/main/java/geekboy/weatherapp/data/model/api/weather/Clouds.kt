package geekboy.weatherapp.data.model.api.weather

import com.google.gson.annotations.SerializedName

data class Clouds(
        @SerializedName("all")val all: Int
)