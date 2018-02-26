package geekboy.weatherapp.mvvm.search

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import geekboy.weatherapp.data.AppDataManager
import geekboy.weatherapp.utils.other.readJSONFromAsset
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel
@Inject
constructor
(val dataManager: AppDataManager, val context: Context) :
        AndroidViewModel(context as Application) {

    var searchCityMutableData = MutableLiveData<SearchCityJsonData>()

    @SuppressLint("CheckResult")
    fun getCitiesFromJson() {
        val gson = Gson()
        val result = context.readJSONFromAsset()
        Observable.fromCallable({ gson.fromJson(result, SearchCityJsonData::class.java) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    val searchCityJsonData = it
                    Log.d("Result", "${searchCityJsonData.cities.size}")
                    searchCityMutableData.postValue(searchCityJsonData)

                })
    }

}
