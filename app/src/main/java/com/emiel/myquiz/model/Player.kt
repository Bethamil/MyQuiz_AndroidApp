package com.emiel.myquiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Player(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val firstName: String?,

) {
}