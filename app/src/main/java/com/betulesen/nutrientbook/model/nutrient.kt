package com.betulesen.nutrientbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class nutrient (

    @ColumnInfo(name="nutrientName")
    @SerializedName("isim")
    val nutrientName : String?,

    @ColumnInfo(name="nutrientCalorie")
    @SerializedName("kalori")
    val nutrientCalorie : String?,

    @ColumnInfo(name="nutrientCarbohydrate")
    @SerializedName("karbonhidrat")
    val nutrientCarbohydrate : String?,

    @ColumnInfo(name="nutrientProtein")
    @SerializedName("protein")
    val nutrientProtein : String?,

    @ColumnInfo(name="nutrientFat")
    @SerializedName("yag")
    val nutrientFat : String?,

    @ColumnInfo(name="nutrientImage")
    @SerializedName("gorsel")
    val nutrientImage : String?

){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}