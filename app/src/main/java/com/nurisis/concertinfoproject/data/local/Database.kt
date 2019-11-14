package com.nurisis.concertinfoproject.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nurisis.concertinfoproject.entity.ConcertItem

@androidx.room.Database(entities = [ConcertItem::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun concertDao() : ConcertDao

    companion object{

        @Volatile
        private var INSTANCE : Database? = null

        fun getDatabase(context: Context) : Database {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "concert_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }

        }

    }

}