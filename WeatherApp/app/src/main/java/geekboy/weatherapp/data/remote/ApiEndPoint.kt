package geekboy.weatherapp.data.remote

import geekboy.weatherapp.BuildConfig


class ApiEndPoint {
    companion object {
        val ENDPOINT_SERVER_DEV = BuildConfig.BASE_URL
        val ENDPOINT_SERVER_LIVE = BuildConfig.BASE_URL
        val API_KEY = "435e9075f348868c2714fe7c6895efa5"
    }
}