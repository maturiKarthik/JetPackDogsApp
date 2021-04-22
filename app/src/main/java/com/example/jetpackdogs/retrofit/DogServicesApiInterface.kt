package com.example.jetpackdogs.retrofit

import com.example.jetpackdogs.model.DogBreed
import io.reactivex.Single
import retrofit2.http.GET

interface DogServicesApiInterface {
    @GET("DevTides/DogsApi/master/dogs.json")
    fun getListOfAllDogs(): Single<List<DogBreed>> // Here he used Single of Rxjava
}