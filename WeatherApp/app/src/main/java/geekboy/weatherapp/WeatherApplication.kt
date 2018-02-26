package geekboy.weatherapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import geekboy.weatherapp.di.component.DaggerAppComponent


class WeatherApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
        return appComponent
    }


}