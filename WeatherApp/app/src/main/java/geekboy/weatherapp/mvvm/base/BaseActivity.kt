package geekboy.weatherapp.mvvm.base

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import dagger.android.AndroidInjection
import geekboy.weatherapp.utils.other.NetworkUtils

abstract class BaseActivity<out V : AndroidViewModel>: AppCompatActivity(), BaseFragment.Callback {

    var lifeCycleRegistry = LifecycleRegistry(this)
    private lateinit var mViewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
    }



    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
        mViewModel = getViewModel()
    }

    override fun getLifecycle(): Lifecycle {
        return lifeCycleRegistry
    }

    fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(applicationContext)

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V


}