package com.example.jetpackdogs.room

import android.content.Context
import androidx.room.*
import com.example.jetpackdogs.model.DogBreed

// DAO class
@Dao
interface DogBreedDao {
    @Query("SELECT * FROM dogtable")
    suspend fun getAll(): List<DogBreed>

    @Insert
    suspend fun insertAll(dogBreedDb: List<DogBreed>)

    @Insert
    suspend fun insert(dogBreedDb: DogBreed)

    @Query("DELETE FROM dogtable")
    suspend fun deleteAll()
}

// AppDatabase
@Database(entities = arrayOf(DogBreed::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDogBreedDao(): DogBreedDao
}

//Room databaseBuilder
object RoomDatabaseBuilder {
    private lateinit var database: AppDatabase
    private lateinit var dogBreedDao: DogBreedDao
    fun build(context: Context) {
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "dog-database"
        ).build()
        dogBreedDao = database.getDogBreedDao()
    }

    suspend fun deleteAll() {
        dogBreedDao.deleteAll()
    }

    suspend fun insertAllElements(dogBreedDb: List<DogBreed>) {
        dogBreedDao.insertAll(dogBreedDb)
    }

    suspend fun getAll(): List<DogBreed> {
        return dogBreedDao.getAll()
    }
}