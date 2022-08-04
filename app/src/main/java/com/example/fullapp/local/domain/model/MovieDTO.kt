package com.example.fullapp.local.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fullapp.remote.domain.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String
) : Parcelable


@Entity(tableName = "favorites_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String

)
