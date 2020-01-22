package hu.ecity.weatherdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import hu.ecity.weatherdemo.data.WeatherResult
import hu.ecity.weatherdemo.http.WeatherAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherAPI::class.java)

        btnGet.setOnClickListener {
            val weatherCall = weatherApi.getWeather(
                etCity.text.toString(),"metric", "f3d694bc3e1d44c1ed5a97bd1120e8fe"
            )

            weatherCall.enqueue(object : Callback<WeatherResult>{
                override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                    t.printStackTrace()
                    tvResult.text = t.message
                }

                override fun onResponse(
                    call: Call<WeatherResult>,
                    response: Response<WeatherResult>
                ) {
                    var result =
                        "Description: ${response.body()?.weather?.get(0)?.description}\n" +
                        "Temperature: ${response.body()?.main?.temp}"

                    tvResult.text = result

                    //http://openweathermap.org/img/w/50d.png

                    Glide.with(this@MainActivity).load(
                        "https://openweathermap.org/img/w/"+
                                response.body()?.weather?.get(0)?.icon+".png").into(ivIcon)
                }
            })
        }
    }
}
