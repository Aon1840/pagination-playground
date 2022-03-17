package com.bearman.pagination_playground.domain.usecase

import com.bearman.pagination_playground.data.room.entity.UserEntity
import com.bearman.pagination_playground.domain.executor.PostExecutionThread
import com.bearman.pagination_playground.domain.executor.ThreadExecutor
import com.bearman.pagination_playground.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllUserUseCase
@Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val repository: UserRepository
) : BaseSingleUseCase<List<UserEntity>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: Void?): Single<List<UserEntity>> {
        return repository.getAllUser()
    }

}