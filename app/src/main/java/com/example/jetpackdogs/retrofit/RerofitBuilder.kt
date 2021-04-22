package com.example.jetpackdogs.retrofit

import com.example.jetpackdogs.model.DogBreed
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val baseUrl = "https://raw.githubusercontent.com/"
    private lateinit var dogApi: DogServicesApiInterface

    init {
        build()
    }

    private fun build() {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Converts Json to MODLE class
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Converts JSON TO Single Create's the Single
            .build()
        dogApi = retrofit.create(DogServicesApiInterface::class.java)
    }

    /**
     * Single is the RxJava Object which emits Data Once
     * & then finishes
     */
    fun getAllDogs(): Single<List<DogBreed>> =  dogApi.getListOfAllDogs()

}