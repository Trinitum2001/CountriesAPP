package com.example.parcial1
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {
    @GET("v3.1/all")
    fun getAllCountries(): Call<List<Country>>
}