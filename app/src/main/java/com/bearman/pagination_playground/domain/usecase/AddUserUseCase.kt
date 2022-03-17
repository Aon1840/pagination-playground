package com.bearman.pagination_playground.domain.usecase

import com.bearman.pagination_playground.data.room.entity.UserEntity
import com.bearman.pagination_playground.domain.executor.PostExecutionThread
import com.bearman.pagination_playground.domain.executor.ThreadExecutor
import com.bearman.pagination_playground.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddUserUseCase
@Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val repository: UserRepository
) : BaseCompletable<AddUserUseCase.Param>(
    threadExecutor,
    postExecutionThread,
) {
    override fun buildUseCaseCompletable(params: Param): Completable {
            return repository.addUser(params.user)
    }

    class Param(val user: UserEntity)
}