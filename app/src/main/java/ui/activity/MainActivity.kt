package com.example.suger.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import domain.command.RequestForecastCommand
import domain.model.ForecastList
import org.class01.forecastList.ForecastListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.*;
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        forecastListView.layoutManager = LinearLayoutManager(this)
        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                println("result is >>> " + result)
                updateUI(forecastListView, result);
            }
        }
    }


    private fun updateUI( t: RecyclerView, result : ForecastList){

        var forecastAdapter = ForecastListAdapter(result){
            toast(it.date)
        }

        t.adapter = forecastAdapter
    }

}
