package domain.datasource

import data.server.ForecastResult
import domain.model.ForecastList

import data.server.Forecast
import java.text.DateFormat
import java.util.*
import domain.model.Forecast as ModelForecast


public class ForecastDataMapper {

    fun convertFromDataModel(forecast : ForecastResult) :ForecastList{
        return ForecastList(forecast.city.name,forecast.city.country,convertForecatListToDomain(forecast.list) )
    }

    private fun convertForecatListToDomain(list:List<Forecast>):List<ModelForecast>{
        return list.map { convertForecatItemToDomain(it) }
    }

    private fun convertForecatItemToDomain(forecast: Forecast):ModelForecast{
        return ModelForecast(convertDate(forecast.dt)
                ,forecast.weather[0].description,forecast.temp.max.toInt(),forecast.temp.min.toInt(),forecast.weather[0].icon)
    }

    private fun convertDate(date:Long):String{
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun  generateIconUrl(iconCode:String) :String{
        return "http://openweathermap.org/img/w/$iconCode.png";
    }
}