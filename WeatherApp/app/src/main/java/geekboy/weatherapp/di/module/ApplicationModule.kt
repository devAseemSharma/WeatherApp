package geekboy.weatherapp.di.module

import android.app.Application
import android.content.Context
import com.sonicapp.di.qualifiers.PreferenceInfo
import dagger.Module
import dagger.Provides
import geekboy.weatherapp.data.AppDataManager
import geekboy.weatherapp.data.DataManager
import geekboy.weatherapp.data.local.pref.AppPreferenceManager
import geekboy.weatherapp.data.local.pref.PreferenceSource
import geekboy.weatherapp.data.remote.RemoteDataManager
import geekboy.weatherapp.data.remote.RemoteSource
import geekboy.weatherapp.utils.PREF_NAME
import geekboy.weatherapp.viewmodel.ViewModelModule
import javax.inject.Singleton


@Module(includes = [(ViewModelModule::class)])
class ApplicationModule {
    @Provides
    @Singleton
    internal fun bindContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun getRemoteSource(remoteDataManager: RemoteDataManager): RemoteSource = remoteDataManager

    @Provides
    @Singleton
    internal fun getPreferenceSource(appPreferenceManager: AppPreferenceManager):
            PreferenceSource = appPreferenceManager

    @Provides
    @Singleton
    internal fun provideDataManger(appDataManager: AppDataManager): DataManager = appDataManager



    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return PREF_NAME
    }
}