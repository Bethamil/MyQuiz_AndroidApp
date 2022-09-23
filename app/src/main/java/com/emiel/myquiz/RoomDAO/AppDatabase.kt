package com.emiel.myquiz.RoomDAO


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emiel.myquiz.model.Player

@Database(entities = [Player :: class], version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDAO() : PlayerDAO

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "scores"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }

}