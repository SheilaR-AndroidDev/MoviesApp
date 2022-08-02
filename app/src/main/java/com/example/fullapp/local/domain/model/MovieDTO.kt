package com.example.fullapp.local.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fullapp.remote.domain.model.Genre

@Entity(tableName = "favorites_table")
data class MovieDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String

)
