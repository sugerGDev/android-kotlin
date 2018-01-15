package ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

object DelegatesExt{
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>();
    fun <T> preference(context:Context, name:String,default:T) = Preference(context,name,default)
}


 class NotNullSingleValueVar<T>(){
    private var _value : T ? = null

    operator fun getValue(thisRef: Any?, property:KProperty< * >):T =
            _value ?: throw IllegalStateException("${property.name} not initialized")


    operator fun setValue(thisRef: Any?, property: KProperty<*>, value :T){
        this._value = if (this._value == null) value
        else
            throw  IllegalStateException("${property.name} already initialized")
    }
}


 class Preference<T>(private val context: Context, private val name: String, private val default: T) {

    private val perfs: SharedPreferences by lazy {
        context.getSharedPreferences("default",Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?,  property: KProperty<*>): T = findPreference(name,default)


    operator fun setValue(thisRef: Any?, property: KProperty<*>, value:T) {
        putPreference(name,value)
    }


    private fun findPreference(name: String,default:T):T = with(perfs){
        val res :Any = when(default){
            is Long -> getLong(name,default)
            is String -> getString(name,default)
            is Int -> getInt(name,default)
            is Boolean -> getBoolean(name,default)
            is Float -> getFloat(name,default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun putPreference(name: String,value:T) = with(perfs.edit()){
        when(value){
            is Long -> putLong(name,value)
            is String -> putString(name,value)
            is Int -> putInt(name,value)
            is Boolean -> putBoolean(name,value)
            is Float -> putFloat(name,value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }
}
