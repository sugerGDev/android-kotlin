package data.server

import android.util.Log
import com.google.gson.Gson
import java.net.URL

public class ForecastListRequest(val zipCode:String) {

    companion object {
        private var APP_ID:String = "15646a06818f61f7b8d7823ca833e1ce"
        private var APP_HOST:String = "http://api.openweathermap.org" + "/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private var APP_URL = "${APP_HOST}&APPID=${APP_ID}&q="
    }


    public fun execute() : ForecastResult {
        val eUrl = APP_URL+zipCode;
        println("url is >>" + eUrl)
        val forecastJsonStr = URL(eUrl).readText()
        println(forecastJsonStr)
        return Gson().fromJson(forecastJsonStr,ForecastResult::class.java)
    }
}