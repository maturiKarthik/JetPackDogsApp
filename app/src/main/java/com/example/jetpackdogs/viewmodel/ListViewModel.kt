package com.example.jetpackdogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackdogs.model.DogBreed
import com.example.jetpackdogs.retrofit.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Here we get the Data From Backend Api / DataBase
 */
class ListViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    //Init of MutableLive Data
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun loadDataFromApi() {
        fetchDataFromRemoteRetrofit()
    }

    // Fetching Data From Retrofit
    private fun fetchDataFromRemoteRetrofit() {
        loading.value = true
        /** Should always put on the background thread**/
        disposable.add(
            RetrofitBuilder.getAllDogs().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(t: List<DogBreed>) {
                        dogs.value = t
                        dogsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}