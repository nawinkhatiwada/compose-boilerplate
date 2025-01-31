package com.devfinity.composeboilerplate.persistance.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devfinity.composeboilerplate.BuildConfig
import com.devfinity.composeboilerplate.persistance.db.dao.UserDao
import com.devfinity.composeboilerplate.persistance.db.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        @Volatile
        var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = createInstance(context)
            }
            return instance as AppDatabase
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, DbConstants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getUserDao(): UserDao

}