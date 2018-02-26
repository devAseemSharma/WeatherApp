package geekboy.weatherapp.data.remote

import android.arch.lifecycle.LiveData
import geekboy.weatherapp.data.model.api.weather.WeatherResponse
import geekboy.weatherapp.data.model.api.weather.request.CityWeatherRequest
import geekboy.weatherapp.data.model.api.weather.request.GeoWeatherRequest
import geekboy.weatherapp.utils.retrofit.ApiResponse
import geekboy.weatherapp.utils.retrofit.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject





class RemoteDataManager @Inject constructor() : RemoteSource {
    private var iApiMethods: IApiMethods

    private val client = OkHttpClient.Builder()
    private val retrofitInstance = Retrofit.Builder()

            .baseUrl(ApiEndPoint.ENDPOINT_SERVER_DEV)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())

            .build()

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        client.addInterceptor(loggingInterceptor)

        iApiMethods = retrofitInstance.create(IApiMethods::class.java)

    }


    override fun getWeatherFromGeo(geoWeatherRequest: GeoWeatherRequest): LiveData<ApiResponse<WeatherResponse>> {
        return iApiMethods.getWeatherWithGeoData(geoWeatherRequest.lat, geoWeatherRequest.lon,
                GeoWeatherRequest.cnt,GeoWeatherRequest.unit,GeoWeatherRequest.API_KEY)
    }

    override fun getWeatherFromCityId(cityWeatherRequest: CityWeatherRequest): LiveData<ApiResponse<WeatherResponse>> {
        return iApiMethods.getWeatherWithCityData(cityWeatherRequest.cityId,
                GeoWeatherRequest.cnt,GeoWeatherRequest.unit,GeoWeatherRequest.API_KEY)
    }
}