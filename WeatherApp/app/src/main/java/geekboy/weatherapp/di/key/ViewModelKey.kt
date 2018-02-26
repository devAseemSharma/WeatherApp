package geekboy.weatherapp.di.key

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@MustBeDocumented
@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value:KClass<out ViewModel>)