package geekboy.weatherapp.mvvm.main

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import geekboy.weatherapp.R
import geekboy.weatherapp.data.model.api.weather.WeatherList
import geekboy.weatherapp.data.model.api.weather.request.CityWeatherRequest
import geekboy.weatherapp.data.model.api.weather.request.GeoWeatherRequest
import geekboy.weatherapp.mvvm.base.BaseActivity
import geekboy.weatherapp.mvvm.search.SearchActivity
import geekboy.weatherapp.viewmodel.AppViewModelFactory
import io.nlopez.smartlocation.SmartLocation
import io.nlopez.smartlocation.rx.ObservableFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject


@RuntimePermissions
class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var appViewModel: AppViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    private lateinit var progressDialog: ProgressDialog

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProviders.of(this, appViewModel).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestLocationWithPermissionCheck()
        searchCity.setOnClickListener({ startSearchActivity() })

        progressDialog = indeterminateProgressDialog(message = "Please wait a bit…", title = "Fetching data")
        progressDialog.cancel()

        mainViewModel.weatherResponse.observeForever({
            toolbar.title = "${it?.city?.name}, ${it?.city?.country}"
            progressDialog.cancel()
            loadWeatherList(ArrayList(it?.weatherList))
        })

        mainViewModel.cityDataWeatherResponse.observeForever({
            toolbar.title = "${it?.city?.name}, ${it?.city?.country}"
            progressDialog.cancel()
            loadWeatherList(ArrayList(it?.weatherList))
        })

        mainViewModel.errorObserver.observeForever({
            val message: CharSequence = it!!
            toast(message)
        })

        currentCity.setOnClickListener({
            requestLocation()
        })

    }

    private fun loadWeatherList(weatherList: ArrayList<WeatherList>) {

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = WeatherAdapter(weatherList)
        }
    }

    @NeedsPermission(ACCESS_FINE_LOCATION)
    fun requestLocation() {
        val locationObservable = ObservableFactory.from(SmartLocation.with(this@MainActivity).location().oneFix())
        locationObservable.subscribe { showLocationData(it) }

    }

    fun showDeniedForLocation() {
        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        System.exit(0)
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated method
        onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun startSearchActivity() {
        val intent = Intent(this@MainActivity, SearchActivity::class.java)
        startActivityForResult(intent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            val bundle = data?.extras
            getCityWeatherData(bundle!!.getString("city_id"))

        }
    }

    fun getCityWeatherData(cityId: String) {
        val cityWeatherRequest = CityWeatherRequest(cityId)
        progressDialog.show()
        mainViewModel.getWeatherFromCity(cityWeatherRequest)
    }


    fun showLocationData(location: Location?) {
        Log.d("Location", "Lat${location?.latitude} Long${location?.longitude}")
        progressDialog.show()
        val geoWeatherRequest = GeoWeatherRequest("${location?.latitude}", "${location?.longitude}")
        mainViewModel.getWeatherFromGeo(geoWeatherRequest)
    }

    private fun showErrorMessage(throwable: Throwable, methodName: String) {
        toast("$methodName Error: ${throwable.message}")
    }
}
