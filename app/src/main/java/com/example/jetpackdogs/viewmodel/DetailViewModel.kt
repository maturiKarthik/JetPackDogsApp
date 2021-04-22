package com.example.jetpackdogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackdogs.model.DogBreed

/**
 * Fetches  the resources
 */
class DetailViewModel : ViewModel() {

    val dogDetail = MutableLiveData<DogBreed>()

    fun uploadData(dogBreed: DogBreed?) {
        dogDetail.value = dogBreed
    }
}