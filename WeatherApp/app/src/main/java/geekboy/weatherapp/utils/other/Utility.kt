package geekboy.weatherapp.utils.other

import android.content.Context
import android.text.format.Time
import geekboy.weatherapp.R
import java.io.IOException
import java.nio.charset.Charset
import java.text.SimpleDateFormat


fun Context.readJSONFromAsset(): String {
    var json: String? = null
    try {
        val `is` = assets.open("output.json")
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        json = String(buffer, Charset.forName("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return ""
    }

    return json
}

class Utility {

    companion object {


        val DATE_FORMAT = "yyyyMMdd"

        /**
         * Converts db date format to the format "Month day", e.g "June 24".
         * @param context Context to use for resource localization
         * @param dateInMillis The db formatted date string, expected to be of the form specified
         * in Utility.DATE_FORMAT
         * @return The day in the form of a string formatted "December 6"
         */
        fun getFormattedMonthDay(context: Context, dateInMillis: Long): String {
            val time = Time()
            time.setToNow()
            val dbDateFormat = SimpleDateFormat(Utility.DATE_FORMAT)
            val monthDayFormat = SimpleDateFormat("MMMM dd hh:mm")
            return monthDayFormat.format(dateInMillis)
        }

        fun formatTemperature(context: Context, temperature: Double): String {
            // Data stored in Celsius by default.  If user prefers to see in Fahrenheit, convert
            // the values here.
            val suffix = "\u00B0"
//            if (!isMetric(context))

//                temperature = temperature * 1.8 + 32
            val roundedString = String.format("%.1f",temperature)

            // For presentation, assume the user doesn't care about tenths of a degree.
            return "$roundedString ${suffix}C"
        }

        fun formatDateTime(context: Context, dateTime: String): String{
            val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = fmt.parse(dateTime)

            val fmtOut = SimpleDateFormat("MMM dd EEE, HH:mm")
            return fmtOut.format(date)
        }

        fun getFormattedWind(context: Context, windSpeed: Float, degrees: Float): String {
            var windSpeed = windSpeed
            val windFormat: Int
//        if (Utility.isMetric(context)) {
            windFormat = R.string.format_wind_kmh
//        } else

//            windFormat = R.string.format_wind_mph
//            windSpeed = .621371192237334f * windSpeed


            // From wind direction in degrees, determine compass direction as a string (e.g NW)
            // You know what's fun, writing really long if/else statements with tons of possible
            // conditions.  Seriously, try it!
            var direction = "Unknown"
            if (degrees >= 337.5 || degrees < 22.5) {
                direction = "N"
            } else if (degrees >= 22.5 && degrees < 67.5) {
                direction = "NE"
            } else if (degrees >= 67.5 && degrees < 112.5) {
                direction = "E"
            } else if (degrees >= 112.5 && degrees < 157.5) {
                direction = "SE"
            } else if (degrees >= 157.5 && degrees < 202.5) {
                direction = "S"
            } else if (degrees >= 202.5 && degrees < 247.5) {
                direction = "SW"
            } else if (degrees >= 247.5 && degrees < 292.5) {
                direction = "W"
            } else if (degrees >= 292.5 && degrees < 337.5) {
                direction = "NW"
            }
            return String.format(context.getString(windFormat), windSpeed, direction)
        }

        /**
         * Helper method to provide the icon resource id according to the weather condition id returned
         * by the OpenWeatherMap call.
         * @param weatherId from OpenWeatherMap API response
         * @return resource id for the corresponding icon. -1 if no relation is found.
         */
        fun getIconResourceForWeatherCondition(weatherId: Int): Int {
            // Based on weather code data found at:
            // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
            if (weatherId in 200..232) {
                return R.drawable.ic_storm
            } else if (weatherId in 300..321) {
                return R.drawable.ic_light_rain
            } else if (weatherId in 500..504) {
                return R.drawable.ic_rain
            } else if (weatherId == 511) {
                return R.drawable.ic_snow
            } else if (weatherId in 520..531) {
                return R.drawable.ic_rain
            } else if (weatherId in 600..622) {
                return R.drawable.ic_snow
            } else if (weatherId in 701..761) {
                return R.drawable.ic_fog
            } else if (weatherId == 761 || weatherId == 781) {
                return R.drawable.ic_storm
            } else if (weatherId == 800) {
                return R.drawable.ic_clear
            } else if (weatherId == 801) {
                return R.drawable.ic_light_clouds
            } else if (weatherId in 802..804) {
                return R.drawable.ic_cloudy
            }
            return -1
        }

        /**
         * Helper method to provide the art resource id according to the weather condition id returned
         * by the OpenWeatherMap call.
         * @param weatherId from OpenWeatherMap API response
         * @return resource id for the corresponding icon. -1 if no relation is found.
         */
        fun getArtResourceForWeatherCondition(weatherId: Int): Int {
            // Based on weather code data found at:
            // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
            if (weatherId in 200..232) {
                return R.drawable.art_storm
            } else if (weatherId in 300..321) {
                return R.drawable.art_light_rain
            } else if (weatherId in 500..504) {
                return R.drawable.art_rain
            } else if (weatherId == 511) {
                return R.drawable.art_snow
            } else if (weatherId in 520..531) {
                return R.drawable.art_rain
            } else if (weatherId in 600..622) {
                return R.drawable.art_snow
            } else if (weatherId in 701..761) {
                return R.drawable.art_fog
            } else if (weatherId == 761 || weatherId == 781) {
                return R.drawable.art_storm
            } else if (weatherId == 800) {
                return R.drawable.art_clear
            } else if (weatherId == 801) {
                return R.drawable.art_light_clouds
            } else if (weatherId in 802..804) {
                return R.drawable.art_clouds
            }
            return -1
        }
    }
}
