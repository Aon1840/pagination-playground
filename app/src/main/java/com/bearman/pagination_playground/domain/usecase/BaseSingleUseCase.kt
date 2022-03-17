package com.bearman.pagination_playground.domain.usecase

import com.bearman.pagination_playground.domain.executor.PostExecutionThread
import com.bearman.pagination_playground.domain.executor.ThreadExecutor
import io.reactivex.Single

abstract class BaseSingleUseCase<Results, in Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : BaseUseCase(threadExecutor, postExecutionThread) {

    abstract fun buildUseCaseSingle(params: Params? = null): Single<Results>

    fun execute(
        onSuccess: (Results) -> Unit,
        onError: (Throwable) -> Unit,
        params: Params? = null
    ) {
        val single = buildUseCaseSingleWithSchedulers(params)
        addDisposable(single.subscribe(onSuccess, onError))
    }

    private fun buildUseCaseSingleWithSchedulers(params: Params?): Single<Results> {
        return buildUseCaseSingle(params)
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
    }
}