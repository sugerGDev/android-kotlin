package domain.datasource

import domain.model.Forecast
import domain.model.ForecastList

interface ForecastDataSource {
    fun  requestForecastByZipCode(zipCode :Long,date:Long):ForecastList?
    fun  requestDayForecast(id:Long):Forecast?

}