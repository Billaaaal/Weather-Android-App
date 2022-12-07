package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        var mPrefs = getSharedPreferences("CitySaved", 0)
        var CitySaved = mPrefs.getString("CitySaved", "London")
        //var mEditor = mPrefs.edit()
        //

        //https://api.openweathermap.org/data/2.5/onecall?lat=-0.1257&lon=51.5085&appid=49162d983649b7cd5e7eda3473bc246d&exclude=current,minutely,hourly,alerts

        var searchbar = findViewById<androidx.appcompat.widget.SearchView>(R.id.searchbar)

        searchbar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                getMethod(p0.toString())
                searchbar.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })


        //add the ability to save the city automaticaly every time we search for one so update it cf.




        //searchbar.setIconifiedByDefault(false)



        var co: Job

        getMethod(CitySaved.toString()) ///replace with getMethod(CityNameSavec) //add the ability to save the city automaticaly every time we search for one so update it














    }

    fun getMethod(CityName:String): String {



        var CityNameContainer = findViewById<TextView>(R.id.CityNameContainer)
        var ImageStatus = findViewById<ImageView>(R.id.ImageStatus)
        var DateContainer = findViewById<TextView>(R.id.DateContainer)
        var TemperatureContainer = findViewById<TextView>(R.id.TemperatureContainer)
        var StatusContainer = findViewById<TextView>(R.id.StatusContainer)
        var WindContainer = findViewById<TextView>(R.id.WindContainer)
        var HumContainer = findViewById<TextView>(R.id.HumContainer)
        var forecastbutton = findViewById<Button>(R.id.forecastbutton)

        var prettyJson = ""

        var urlMain = "https://api.openweathermap.org/data/2.5/"





        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(urlMain)
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)



        var co = CoroutineScope(Dispatchers.IO).launch {

            var response = service.getEmployees(CityName)

            Log.d("TAG", "onResponse: ConfigurationListener::"+ response.raw().request().url())

            prettyJson = response.body().toString()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    var mPrefs = getSharedPreferences("CitySaved", 0)
                    var mEditor = mPrefs.edit()
                    //var CitySaved = mPrefs.getString("CitySaved", "London")

                    mEditor.putString("CitySaved", CityName).commit()

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )

                    // get JSONObject from JSON file

                    // fetch JSONObject named employee

                    // get employee name and salary
                    //var name = employee.getString("name")


                    //val jsonObject = JSONTokener(prettyJson).nextValue() as JSONObject

                    // ID
                    //val id = jsonObject.getString("coord")


                    var obj = JSONObject(prettyJson)

                    var coord: JSONObject = obj.getJSONObject("coord")
                    var weather: JSONArray = obj.getJSONArray("weather")
                    var weather_: JSONObject = weather.getJSONObject(0)
                    var main: JSONObject = obj.getJSONObject("main")
                    var wind_: JSONObject = obj.getJSONObject("wind")
                    var clouds: JSONObject = obj.getJSONObject("clouds")
                    var sys: JSONObject = obj.getJSONObject("sys")


                    var lat = coord.getString("lat")
                    var lon = coord.getString("lon")

                    forecastbutton.setOnClickListener {
                        var intent = Intent(this@MainActivity, ForecastActivity::class.java)
                        intent.putExtra("lat", lat)
                        intent.putExtra("lon", lon)
                        startActivity(intent)
                        //overridePendingTransition(R.anim.slide_in, R.anim.slide_out)

                    }






                    var name = coord.getString("lon")
                    var temperature_ = main.getString("temp")
                    var temperature__ = temperature_.toFloat()
                    //var temperature___ = temperature__-273.15
                    var temperature = temperature__.roundToInt().toString()

                    TemperatureContainer.text = temperature



                    val current = LocalDateTime.now()

                    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    val formatted = current.format(formatter)

                    val cal = Calendar.getInstance()
                    val dayOfMonth =  cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
                    var day = cal[Calendar.DAY_OF_MONTH]


                    DateContainer.text = "Today, $day $dayOfMonth"

                    var humidity = main.getString("humidity")+ " %"

                    HumContainer.text = humidity

                    var wind = wind_.getString("speed").replace('.', ',')+ " km/h" ///////////

                    WindContainer.text = wind

                    var StatusDescription = weather_.getString("description").replaceFirstChar { it.uppercase() }

                    StatusContainer.text = StatusDescription

                    //set content view until every value is initialized and then make ....... element.text = .....


                    var Status = weather_.getString("main")

                    var city = obj.getString("name")

                    CityNameContainer.text = city
























                    //Log.i("TEST", prettyJson)
                    //textview.text = prettyJson



                } else {

                    Toast.makeText(this@MainActivity , "Hi", Toast.LENGTH_SHORT)


                    //Log.i("TEST", response.code().toString())


                }



            }

            //Log.i("HUM", prettyJson)
            // return@launch prettyJson

            //val jsonObj = JSONObject(prettyJson)


            //val foodJson = jsonObj.getJSONObject("main")



        }






        //co.join()



        return "OK"

    }





}




