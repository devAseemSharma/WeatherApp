package geekboy.weatherapp.data.local.pref

import android.content.Context
import com.sonicapp.di.qualifiers.PreferenceInfo
import geekboy.weatherapp.data.DataManager
import javax.inject.Inject

class AppPreferenceManager
@Inject
constructor(val context: Context,
            @PreferenceInfo prefFileName: String) : Preferences(), PreferenceSource {
    init {
        Preferences.init(context, prefFileName)
    }

    var userLoggedInMode by intPref("mode")

    var userId by stringPref("userId")
    override fun getCurrentUserLoggedInMode(): Int = userLoggedInMode

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        userLoggedInMode = mode.type
    }

    override fun getCurrentUserId(): String = userId!!

    override fun setCurrentUserId(userID: String) {
        this@AppPreferenceManager.userId = userID
    }

    override fun clearUserData() {
        userLoggedInMode = DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
        userId = ""
    }
}