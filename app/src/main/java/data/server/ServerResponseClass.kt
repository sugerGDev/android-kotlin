package data.server

data class ForecastResult (val city : City, val cod : String , val cnt : Int, val list: List<Forecast>)
data class City (val id :String , val name : String, val country :String , val population :String)
data class Forecast(val dt:Long, val temp: Temperature, val weather:List<Weather>)
data class Temperature (val day:Float, val min:Float, val max:Float, val night:Float, val eve:Float,val morn:Float,val pressure:Float,val humidity:Int,val speed:Float,val deg:Int, val clouds:Int,val rain:Float)
data class Weather(val id :String ,val main:String , val description:String,val icon:String)