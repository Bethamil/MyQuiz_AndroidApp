package com.emiel.myquiz.RoomDAO

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import com.emiel.myquiz.model.Player
@Dao
interface PlayerDAO {
    @Query("SELECT * FROM player")
    suspend fun getAll(): List<Player>


    @Insert
    suspend fun insert(player: Player)
}

