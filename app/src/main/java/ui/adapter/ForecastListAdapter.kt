package org.class01.forecastList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.suger.myapplication.R
import com.squareup.picasso.Picasso
import domain.model.Forecast
import domain.model.ForecastList
import kotlinx.android.synthetic.main.item_forecast.view.*
import ui.util.ctx
import kotlinx.android.synthetic.*

class ForecastListAdapter(val forecastList: ForecastList,
        private val itemClick:(Forecast)->Unit):
        RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder>(){

    /**
     * 自定 ForecastViewHolder
     */
    class ForecastViewHolder(view:View, private val itemClick:(Forecast)->Unit) :
            RecyclerView.ViewHolder(view){

//        private val  iconView:ImageView
//        private val dateView:TextView
//        private val  descriptionView:TextView
//        private val maxTemperatureView:TextView
//        private val mintemperatureView:TextView
          private val  iconPictureUrl:String;
//
        init {
           iconPictureUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516024432844&di=fdba69e78aaaba784e00b9edeae826f1&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140526%2F4499633_224324263000_2.jpg";
//            iconView = view.find(R.id.icon)
//            dateView = view.find(R.id.date)
//            descriptionView = view.find(R.id.description)
//            maxTemperatureView = view.find(R.id.maxTempature)
//            mintemperatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast: Forecast){
            with(forecast){

                Picasso.with(itemView.ctx).load(iconPictureUrl).into(itemView.icon)

                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTempature.text = "${high.toString()}"
                itemView.minTemperature.text = "${low.toString()}"
                itemView.setOnClickListener{itemClick(this)}
            }
        }
    }


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bindForecast(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder{
        val view = LayoutInflater
                .from(parent?.ctx)
                .inflate(R.layout.item_forecast,parent,false)

       return ForecastViewHolder(view,itemClick)
    }
}
