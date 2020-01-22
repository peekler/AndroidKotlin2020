package hu.ecity.moneyhttpdemo.http

import hu.ecity.moneyhttpdemo.data.MoneyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.exchangeratesapi.io   /latest?base=EUR

interface MoneyAPI  {

    @GET("/latest")
    fun getMoney(@Query("base") base: String) : Call<MoneyResult>

}