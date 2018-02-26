package geekboy.weatherapp.data

import geekboy.weatherapp.data.local.pref.PreferenceSource
import geekboy.weatherapp.data.remote.RemoteSource


interface DataManager: RemoteSource, PreferenceSource {

    enum class LoggedInMode private constructor(val type: Int) {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }

}