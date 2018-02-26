package geekboy.weatherapp.mvvm.search

import com.google.gson.annotations.SerializedName

data class SearchCityJsonData(
        @SerializedName("list")val cities: List<SearchCityModel>
)