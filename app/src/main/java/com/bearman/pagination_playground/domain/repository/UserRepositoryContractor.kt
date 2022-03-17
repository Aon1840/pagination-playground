package com.bearman.pagination_playground.domain.repository

import com.bearman.pagination_playground.data.room.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepositoryContractor {
    fun getAllUser(): Single<List<UserEntity>>
    fun getUserById(id: Int): Single<UserEntity>
    fun addUser(userEntity: UserEntity): Completable
}