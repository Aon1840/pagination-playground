package com.bearman.pagination_playground.di

import com.bearman.pagination_playground.data.JobExecutor
import com.bearman.pagination_playground.domain.executor.PostExecutionThread
import com.bearman.pagination_playground.domain.executor.ThreadExecutor
import com.bearman.pagination_playground.view.UIThread
import dagger.Binds
import dagger.Module

@Module
@Suppress("unused")
abstract class ExecutorModule {

    @Binds
    abstract fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread
}