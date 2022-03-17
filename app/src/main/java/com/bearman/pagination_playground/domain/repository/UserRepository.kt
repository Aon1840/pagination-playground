package com.bearman.pagination_playground.domain.repository

import com.bearman.pagination_playground.data.room.dao.UserDao
import com.bearman.pagination_playground.data.room.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository
@Inject constructor(
    private val userDao: UserDao
): UserRepositoryContractor{
    override fun getAllUser(): Single<List<UserEntity>> {
        return userDao.getAllUser()
    }

    override fun getUserById(id: Int): Single<UserEntity> {
        return userDao.getUserById(id)
    }

    override fun addUser(userEntity: UserEntity): Completable {
        return userDao.addUser(userEntity)
    }
}