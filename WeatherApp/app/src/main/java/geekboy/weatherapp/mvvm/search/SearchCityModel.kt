package geekboy.weatherapp.mvvm.search

import com.google.gson.annotations.SerializedName

data class SearchCityModel(var id: String, @SerializedName("nm") var name: String
                           , var lat: String, var lon: String)