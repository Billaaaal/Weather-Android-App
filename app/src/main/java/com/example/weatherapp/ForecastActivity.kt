package com.example.weatherapp

import CustomAdapter
import SecondCustomAdapter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.math.roundToInt

class ForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_layout)
        supportActionBar?.hide()

        //var RecyclerViewPlace = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_view)





        // this creates a vertical layout Manager

        var extras = intent.extras

        var lon = extras!!.getString("lon").toString()

        //Toast.makeText(this, lon.toString(), Toast.LENGTH_SHORT).show()


        getMethod()







    }

    fun getMethod(): String {





        var extras = intent.extras

        var lat = extras!!.getString("lat").toString()
        var lon = extras!!.getString("lon").toString()

        //Log.i("TENHAG", lat)

        var prettyJson = ""

        var urlMain = "https://api.openweathermap.org/data/2.5/"






        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(urlMain)
            .build()

        // Create Service
        val service = retrofit.create(ApiServiceForecast::class.java)



        var co = CoroutineScope(Dispatchers.IO).launch {



            var response = service.getEmployees(lat, lon)



            prettyJson = response.body().toString()

            withContext(Dispatchers.Main) {
                Log.i("TAG", "onResponse: ConfigurationListener::"+ response.raw().request().url())

                if (response.isSuccessful) {



                    //var CitySaved = mPrefs.getString("CitySaved", "London")



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

                    var hourly: JSONArray = obj.getJSONArray("hourly")


                    var h1: JSONObject = hourly.getJSONObject(1)
                    var h2: JSONObject = hourly.getJSONObject(2)
                    var h3: JSONObject = hourly.getJSONObject(3)
                    var h4: JSONObject = hourly.getJSONObject(4)
                    var h5: JSONObject = hourly.getJSONObject(5)

                    
                    var image1 = h1.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image2 = h2.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image3 = h3.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image4 = h4.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image5 = h5.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)







                    var temp1 = h1.getString("temp").toFloat().roundToInt().toString()
                    var temp2 = h2.getString("temp").toFloat().roundToInt().toString()
                    var temp3 = h3.getString("temp").toFloat().roundToInt().toString()
                    var temp4 = h4.getString("temp").toFloat().roundToInt().toString()
                    var temp5 = h5.getString("temp").toFloat().roundToInt().toString()

                    //var date1 = h1.getString("dt")
                    //var date2 = h2.getString("dt")
                    //var date3 = h3.getString("dt")
                    //var date4 = h4.getString("dt")
                    //var date5 = h5.getString("dt")



                    //val dt = Instant.ofEpochSecond(date1.toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime().toString().split("T").toTypedArray()

                    var date1 = Instant.ofEpochSecond(h1.getString("dt").toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime().toString().split("T")[1]
                    var date2 = Instant.ofEpochSecond(h2.getString("dt").toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime().toString().split("T")[1]
                    var date3 = Instant.ofEpochSecond(h3.getString("dt").toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime().toString().split("T")[1]
                    var date4 = Instant.ofEpochSecond(h4.getString("dt").toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime().toString().split("T")[1]
                    var date5 = Instant.ofEpochSecond(h5.getString("dt").toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime().toString().split("T")[1]

                    //Log.i("PSG", System.currentTimeMillis().toLong().toString())



                    var datetoday = ConvertDate_(System.currentTimeMillis().toString())




                    val DateToday = findViewById<TextView>(R.id.DateToday)

                    DateToday.text = datetoday

                    val recyclerview = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_view)


                    recyclerview.layoutManager = LinearLayoutManager(this@ForecastActivity, LinearLayoutManager.HORIZONTAL, false)

                    // ArrayList of class ItemsViewModel
                    val data = ArrayList<ItemsViewModel>()

                    // This loop will create 20 Views containing
                    // the image with the count of view
                    // for (i in 1..5) {
                    //      data.add(ItemsViewModel(R.layout.card_view_design, temp1+"°C", date1.toString()))
                    // }

                    data.add(ItemsViewModel(R.layout.card_view_design, temp1+"°C", date1.toString(), image1))
                    data.add(ItemsViewModel(R.layout.card_view_design, temp2+"°C", date2.toString(), image2))
                    data.add(ItemsViewModel(R.layout.card_view_design, temp3+"°C", date3.toString(),image3))
                    data.add(ItemsViewModel(R.layout.card_view_design, temp4+"°C", date4.toString(), image4))
                    data.add(ItemsViewModel(R.layout.card_view_design, temp5+"°C", date5.toString(), image5))






                    // This will pass the ArrayList to our Adapter
                    val adapter = CustomAdapter(data)

                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter






                    //Log.i("PSG", h5.toString())

                    //Log.i("PSG", prettyJson.toString())

                    var daily: JSONArray = obj.getJSONArray("daily")

                    var j1: JSONObject = daily.getJSONObject(1)
                    var j2: JSONObject = daily.getJSONObject(2)
                    var j3: JSONObject = daily.getJSONObject(3)
                    var j4: JSONObject = daily.getJSONObject(4)
                    var j5: JSONObject = daily.getJSONObject(5)
                    var j6: JSONObject = daily.getJSONObject(6)
                    var j7: JSONObject = daily.getJSONObject(7)


                    var temp1_ = j1.getJSONObject("temp").getString("day").toFloat().roundToInt().toString() + "°"
                    var temp2_ = j2.getJSONObject("temp").getString("day").toFloat().roundToInt().toString() + "°"
                    var temp3_ = j3.getJSONObject("temp").getString("day").toFloat().roundToInt().toString() + "°"
                    var temp4_ = j4.getJSONObject("temp").getString("day").toFloat().roundToInt().toString() + "°"
                    var temp5_ = j5.getJSONObject("temp").getString("day").toFloat().roundToInt().toString() + "°"
                    var temp6_ = j6.getJSONObject("temp").getString("day").toFloat().roundToInt().toString() + "°"
                    var temp7_ = j7.getJSONObject("temp").getString("day").toFloat().roundToInt().toString() + "°"

                    //var timee = ConvertDate("156415648")

                    //

                    var date1_ = ConvertDate(j1.getString("dt"))
                    var date2_ = ConvertDate(j2.getString("dt"))
                    var date3_ = ConvertDate(j3.getString("dt"))
                    var date4_ = ConvertDate(j4.getString("dt"))
                    var date5_ = ConvertDate(j5.getString("dt"))
                    var date6_ = ConvertDate(j6.getString("dt"))
                    var date7_ = ConvertDate(j7.getString("dt"))


                    var image1_ = j1.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image2_ = j2.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image3_ = j3.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image4_ = j4.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image5_ = j5.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image6_ = j6.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)
                    var image7_ = j7.getJSONArray("weather").getJSONObject(0).getString("icon").dropLast(1)


                    //Log.i("dateee", date6_.toString())

                    val Secondrecyclerview = findViewById<RecyclerView>(R.id.recycler_view2)
                    Secondrecyclerview.isNestedScrollingEnabled = false

                    // this creates a vertical layout Manager
                    Secondrecyclerview.layoutManager = LinearLayoutManager(this@ForecastActivity)

                    // ArrayList of class ItemsViewModel
                    val Seconddata = ArrayList<SecondItemsViewModel>()

                    // This loop will create 20 Views containing
                    // the image with the count of view

                    Seconddata.add(SecondItemsViewModel(date1_, image1_, temp1_))
                    Seconddata.add(SecondItemsViewModel(date2_, image2_, temp2_))
                    Seconddata.add(SecondItemsViewModel(date3_, image3_, temp3_))
                    Seconddata.add(SecondItemsViewModel(date4_, image4_, temp4_))
                    Seconddata.add(SecondItemsViewModel(date5_, image5_, temp5_))
                    Seconddata.add(SecondItemsViewModel(date6_, image6_, temp6_))
                    Seconddata.add(SecondItemsViewModel(date7_, image7_, temp7_))



                    // This will pass the ArrayList to our Adapter
                    val Secondadapter = SecondCustomAdapter(Seconddata)

                    // Setting the Adapter with the recyclerview
                    Secondrecyclerview.adapter = Secondadapter

                    Log.i("TAG", "onResponse: ConfigurationListener::"+ response.raw().request().url())


                    Log.i("PSG", System.currentTimeMillis().toString().toString() + "AND" + j1.getString("dt").toString())



                    //textview.text = prettyJson



                } else {

                    Toast.makeText(this@ForecastActivity , "Hi", Toast.LENGTH_SHORT)



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

    fun ConvertDate(timepassed:String):String{

        var stamp = Timestamp((timepassed+"000").toLong())
        var date = Date(stamp.time).toString()

        var ho = date.split(" ")[1]
        var he = date.split(" ")[2]
        var toReturn = "$ho. $he"





        //Log.i("Datez", datos.toString()+ ". " +datosz)

        //Log.i("dateee", toReturn.replace(" 0", " ").toString())

        return toReturn.replace(" 0", " ").toString()


    }

    fun ConvertDate_(timepassed:String):String{

        var stamp = Timestamp((timepassed).toLong())
        var date = Date(stamp.time).toString()

        var ho = date.split(" ")[1]
        var he = date.split(" ")[2]
        var toReturn = "$ho, $he"





        //Log.i("Datez", datos.toString()+ ". " +datosz)

        //Log.i("dateee", toReturn.replace(" 0", " ").toString())

        return toReturn.replace(" 0", " ").toString()


    }
}