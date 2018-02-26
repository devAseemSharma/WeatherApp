package geekboy.weatherapp.mvvm.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import geekboy.weatherapp.R



open class SearchCityAdapter(var listCities: ArrayList<SearchCityModel>) : RecyclerView.Adapter<SearchCityAdapter.SearchItemViewHolder>(){
    var context: Context? = null
    var listCityData: ArrayList<SearchCityModel> = listCities

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchItemViewHolder {
        context = parent?.context
        val item = LayoutInflater.from(parent?.context).inflate(R.layout.row_search_cities, parent, false)
        return SearchItemViewHolder(item)
    }


    override fun getItemCount(): Int = listCities.size


    override fun onBindViewHolder(holder: SearchItemViewHolder?, position: Int) {
        val searchCityModel = listCityData[position]
        holder?.tvCityName?.text = searchCityModel.name

        holder?.layoutRoot?.setOnClickListener({
            val searchActivityInstance = context as SearchActivity
            searchActivityInstance.citySelected(listCityData[position].id)

        })
    }

    class SearchItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            ButterKnife.bind(this, view)
        }

        @BindView(R.id.cityName)
        lateinit var tvCityName: TextView

        @BindView(R.id.root)
        lateinit var layoutRoot: LinearLayout

    }

}