package geekboy.weatherapp.utils.other

fun String.isEmailValid(): Boolean{


    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}