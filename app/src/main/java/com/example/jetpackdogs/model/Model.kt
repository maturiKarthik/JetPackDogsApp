package com.example.jetpackdogs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Use the same class a Entity of RoomDatabase
 *
 * @Parcelize is used for Safeargs to pass arguments in
 * the navigation.
 */
//Retrofit Data
@Parcelize
@Entity(tableName = "dogtable")
data class DogBreed(
    val bred_for: String?,
    val breed_group: String?,
    val id: String?,
    val life_span: String?,
    val name: String?,
    val origin: String?,
    val temperament: String?,
    //val weight: Weight,
    val url: String
   // @SerializedName(value = "height")
   // val animalheight: Height
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Parcelize
data class Height(val imperial: String?, val metric: String?) : Parcelable // represents JSON Object

@Parcelize
data class Weight(val imperial: String?, val metric: String?) : Parcelable// represents JSON Object