package geekboy.weatherapp.mvvm.search

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import geekboy.weatherapp.R
import geekboy.weatherapp.mvvm.base.BaseActivity
import geekboy.weatherapp.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.indeterminateProgressDialog
import javax.inject.Inject

class SearchActivity : BaseActivity<SearchViewModel>() {
    @Inject
    lateinit var appViewModel: AppViewModelFactory

    private lateinit var searchViewModel: SearchViewModel

    private var listDisplayed = ArrayList<SearchCityModel>()

    private lateinit var searchCityAdapter: SearchCityAdapter
    override fun getViewModel(): SearchViewModel {
        searchViewModel = ViewModelProviders.of(this, appViewModel).get(SearchViewModel::class.java)
        return searchViewModel
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchCityAdapter = SearchCityAdapter(listDisplayed)
        searchViewModel.getCitiesFromJson()

        val progressDialog = indeterminateProgressDialog(message = "Please wait a bit…", title = "Fetching data")
        progressDialog.show()

        searchViewModel.searchCityMutableData.observeForever({
            val cities = ArrayList(it?.cities)
            // Removed sort as result not looking good with it
//            cities.sortedWith(compareBy { it.name.toLowerCase() }).forEach { listDisplayed.add(it) }
            listDisplayed = cities
            loadCityList()
            progressDialog.cancel()
        })
    }

    private fun loadCityList() {
        rvSearchCities.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
            adapter = SearchCityAdapter(listDisplayed)

        }
    }

    fun citySelected(cityID: String) {
        Log.d("CitySelected", "City: $cityID")
        val bundle = Bundle()
        bundle.putString("city_id",cityID)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

}
