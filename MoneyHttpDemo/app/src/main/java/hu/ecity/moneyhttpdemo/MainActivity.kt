package hu.ecity.moneyhttpdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ecity.moneyhttpdemo.data.MoneyResult
import hu.ecity.moneyhttpdemo.http.MoneyAPI
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

        // https://api.exchangeratesapi.io/latest?base=EUR
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val moneyService = retrofit.create(MoneyAPI::class.java)

        btnGet.setOnClickListener {
            val moneyCall = moneyService.getMoney("EUR")
            moneyCall.enqueue(object : Callback<MoneyResult>{
                override fun onFailure(call: Call<MoneyResult>, t: Throwable) {

                    t.printStackTrace()
                    tvResult.text = t.message

                }

                override fun onResponse(call: Call<MoneyResult>, response: Response<MoneyResult>) {

                    tvResult.text = "HUF: ${response.body()?.rates?.HUF}"

                }
            })
        }
    }

}
