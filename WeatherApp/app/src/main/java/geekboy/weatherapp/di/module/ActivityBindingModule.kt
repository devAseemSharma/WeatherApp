package geekboy.weatherapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import geekboy.weatherapp.di.qualifiers.ActivityScoped
import geekboy.weatherapp.mvvm.main.MainActivity
import geekboy.weatherapp.mvvm.search.SearchActivity

/**
 * When SubComponent and its builder has no other methods then we can use ContributesAndroidInjector
 * to generate
 *
 * **/

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun searchActivity(): SearchActivity
}
