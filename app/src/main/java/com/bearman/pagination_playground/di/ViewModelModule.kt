package com.bearman.pagination_playground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bearman.pagination_playground.view.viewModel.AddUserViewModel
import com.bearman.pagination_playground.view.viewModel.GetAllUserViewModel
import com.bearman.pagination_playground.view.viewModel.ViewModelFactory
import com.bearman.pagination_playground.view.viewModel.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GetAllUserViewModel::class)
    abstract fun bindGetAllUserViewModel(viewModel: GetAllUserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddUserViewModel::class)
    abstract fun bindAddUserViewModel(viewModel: AddUserViewModel): ViewModel
}