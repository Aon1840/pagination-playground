package com.bearman.pagination_playground.domain.usecase

import com.bearman.pagination_playground.domain.executor.PostExecutionThread
import com.bearman.pagination_playground.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver

abstract class BaseCompletable<in Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : BaseUseCase(threadExecutor, postExecutionThread) {

    abstract fun buildUseCaseCompletable(params: Params): Completable

    fun execute(
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit,
        params: Params) {
        val completable = buildUseCaseCompletableWithSchedulers(params)
        addDisposable(completable.subscribe(onSuccess, onError))
    }

    private fun buildUseCaseCompletableWithSchedulers(params: Params): Completable {
        return buildUseCaseCompletable(params)
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
    }

}