package geekboy.weatherapp.data.local.pref

import geekboy.weatherapp.data.DataManager


interface PreferenceSource {

    fun getCurrentUserLoggedInMode(): Int
    fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode)
    fun getCurrentUserId(): String
    fun setCurrentUserId(userID: String)
    fun clearUserData()

}