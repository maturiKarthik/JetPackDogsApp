package com.example.jetpackdogs.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.jetpackdogs.Utils.SharedPreferencesHelper
import com.example.jetpackdogs.model.DogBreed
import com.example.jetpackdogs.retrofit.RetrofitBuilder
import com.example.jetpackdogs.room.RoomDatabaseBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * Here we get the Data From Backend Api / DataBase
 */
class ListViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private lateinit var context: Context
    private var preferencesHelper = SharedPreferencesHelper(getApplication())
    private var refreshTimeInterval = 5 * 60 * 1000 * 1000 * 1000L // 5minutes in nana second
    private var refreshTimeIntervalTest = 10 * 1000 * 1000 * 1000L // 10 sec in nana second

    init {
        RoomDatabaseBuilder.build(context = getApplication())
    }

    //Init of MutableLive Data
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun setContext(context: Context) {
        this.context = context
    }

    fun retrieveData() {
        val updateTime = preferencesHelper.getUpdateTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTimeInterval) {
            getDataFromDb()
            Toast.makeText(getApplication(), "Dogs retrived from Db", Toast.LENGTH_SHORT).show()
        } else {
            loadDataFromApi()
            Toast.makeText(getApplication(), "Dogs retrived API", Toast.LENGTH_SHORT).show()
        }
    }

    fun loadDataFromApi() {
        fetchDataFromRemoteRetrofit()
    }

    private fun getDataFromDb() {
        loading.value = true
        dogsLoadError.value = false
        launch {
            loading.value = false
            dogs.value = RoomDatabaseBuilder.getAll()
        }
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
                        storeDataLocally(t)
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

    /**
     * RoomDatabase storing
     * */

    fun storeDataLocally(dogBreedDb: List<DogBreed>) {
        launch {
            RoomDatabaseBuilder.deleteAll()
            RoomDatabaseBuilder.insertAllElements(dogBreedDb)
        }
        preferencesHelper.saveUpdateTime(System.nanoTime())
    }
}