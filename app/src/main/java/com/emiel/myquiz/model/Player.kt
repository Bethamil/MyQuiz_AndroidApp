package com.emiel.myquiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Player(
    @PrimaryKey(autoGenerate = true) var id : Int?  = null,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "score") val score: Int?,


) {

}