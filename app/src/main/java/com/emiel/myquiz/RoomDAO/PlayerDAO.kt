package com.emiel.myquiz.RoomDAO

import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import com.emiel.myquiz.model.Player

interface PlayerDAO {
    @Query("SELECT * FROM player")
    fun getAll(): List<Player>


    @Insert
    fun insert(player: Player)
}

@Database(entities = [Player::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDAO(): PlayerDAO
}
