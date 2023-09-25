package com.example.weatherly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// e48b07c19ba7c56d4422a9bd04014c52

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchWeatherData()
    }
    private fun fetchWeatherData(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(APIinterface::class.java)

        val response = retrofit.getWeatherData("mumbai", "e48b07c19ba7c56d4422a9bd04014c52", "metric")
        response.enqueue(object : Callback<Weatherly>{
            override fun onResponse(call: Call<Weatherly>, response: Response<Weatherly>) {
                val responseBody = response.body()
                if( response.isSuccessful && responseBody != null){
                    val temperature = responseBody.main.temp.toString()
                    Log.d("TAG", "onResponse: $temperature")
                }
            }

            override fun onFailure(call: Call<Weatherly>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}