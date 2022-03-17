package com.bearman.pagination_playground.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bearman.pagination_playground.data.room.dao.UserDao
import com.bearman.pagination_playground.data.room.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DATBASE_NAME = "user_database"

        fun getAppDatabase(context: Context): UserDatabase =
            Room.databaseBuilder(context, UserDatabase::class.java, DATBASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}