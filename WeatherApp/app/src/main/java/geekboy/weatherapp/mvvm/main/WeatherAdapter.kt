package geekboy.weatherapp.mvvm.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import geekboy.weatherapp.R
import geekboy.weatherapp.data.model.api.weather.WeatherList
import geekboy.weatherapp.utils.other.Utility

class WeatherAdapter(var listWeather: ArrayList<WeatherList>) : RecyclerView.Adapter<WeatherAdapter.ViewHolderWeather>() {

    private var itemListWeather: ArrayList<WeatherList> = listWeather

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderWeather {
        context = parent?.context
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_weather, parent, false)
        return ViewHolderWeather(view)
    }

    override fun getItemCount(): Int = itemListWeather.size

    override fun onBindViewHolder(holder: ViewHolderWeather?, position: Int) {
        var weatherList = itemListWeather[position]

        holder?.tvTemperature?.text = Utility.formatTemperature(context!!, weatherList.mainTemperature.temp.toDouble())
        holder?.tvWindSpeed?.text = Utility.getFormattedWind(context!!, weatherList.wind.speed.toFloat(), weatherList.wind.degree.toFloat())
        holder?.tvWeatherName?.text = weatherList.weather[0].main
        holder?.ivWeatherIcon?.setImageResource(Utility.getIconResourceForWeatherCondition(weatherList.weather[0].id))
        holder?.tvDateTime?.text = Utility.formatDateTime(context!!,weatherList.dateText)
    }

    class ViewHolderWeather(view: View) : RecyclerView.ViewHolder(view) {
        init {
            ButterKnife.bind(this, view)
        }

        @BindView(R.id.tvTemperature)
        lateinit var tvTemperature: TextView

        @BindView(R.id.tvWindSpeed)
        lateinit var tvWindSpeed: TextView

        @BindView(R.id.tvDateTime)
        lateinit var tvDateTime: TextView

        @BindView(R.id.tvWeatherName)
        lateinit var tvWeatherName: TextView

        @BindView(R.id.weatherIcon)
        lateinit var ivWeatherIcon: ImageView

    }
}