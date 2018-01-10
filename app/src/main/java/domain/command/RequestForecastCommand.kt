package domain.command

import data.server.ForecastListRequest
import domain.datasource.ForecastDataMapper
import domain.model.ForecastList

public class RequestForecastCommand(val zipCode:String)
    :Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastListReq = ForecastListRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel( forecastListReq.execute() )
    }


}