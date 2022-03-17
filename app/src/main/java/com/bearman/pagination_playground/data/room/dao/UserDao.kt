package com.bearman.pagination_playground.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bearman.pagination_playground.data.room.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("select * from user")
    fun getAllUser(): Single<List<UserEntity>>

    @Query("select * from user where id = :id")
    fun getUserById(id: Int): Single<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserEntity): Completable
}