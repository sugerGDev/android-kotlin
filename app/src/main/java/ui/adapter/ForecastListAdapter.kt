package org.class01.forecastList

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import domain.model.ForecastList

class ForecastListAdapter(val forecastList: ForecastList) :
        RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder>(){

    class ForecastViewHolder(var textView: TextView) : RecyclerView.ViewHolder(textView)


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {

//        holder.textView.text = items[position]
        with(forecastList.dailyForecast[position]){
            holder.textView.text = "$date - $description - $high/$low"
        }
    }

    override fun getItemCount(): Int {
        return forecastList.size

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder{
       return ForecastViewHolder(TextView(parent?.context))
    }
}
