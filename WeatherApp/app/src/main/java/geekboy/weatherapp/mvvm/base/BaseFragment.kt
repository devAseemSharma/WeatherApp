package geekboy.weatherapp.mvvm.base

import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import geekboy.weatherapp.mvvm.base.BaseActivity

/**
 * Created by cis on 13/12/17.
 */
abstract class BaseFragment< out V : AndroidViewModel> : Fragment() {
    private var mActivity: BaseActivity<*>? = null
    private var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onAttach(context: Context?) {
        performDependencyInjection()
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity = context as BaseActivity<*>?
            this.mActivity = activity
            activity!!.onFragmentAttached()
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return mActivity
    }


    fun isNetworkConnected(): Boolean {
        return mActivity != null && mActivity!!.isNetworkConnected()
    }

    fun hideKeyboard() {
        if (mActivity != null) {
            mActivity!!.hideKeyboard()
        }
    }

    /*fun openActivityOnTokenExpire() {
          if (mActivity != null) {
              mActivity!!.openActivityOnTokenExpire()
          }
      }*/

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V


    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int
}