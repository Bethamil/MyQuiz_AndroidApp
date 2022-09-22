package com.emiel.myquiz.RoomDAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emiel.myquiz.model.Player

@Dao
interface PlayerDAO {
    @Query("SELECT * FROM player")
    suspend fun getAll(): List<Player>

    @Query("SELECT * FROM player LIMIT 10")
    suspend fun getTopTen(): List<Player>

    @Insert
    suspend fun insert(player: Player)
}

