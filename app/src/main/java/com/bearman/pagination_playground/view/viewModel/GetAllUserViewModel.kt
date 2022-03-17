package com.bearman.pagination_playground.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bearman.pagination_playground.data.room.entity.UserEntity
import com.bearman.pagination_playground.domain.usecase.GetAllUserUseCase
import javax.inject.Inject

class GetAllUserViewModel
@Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase
) : ViewModel() {

    var userList = MutableLiveData<List<UserEntity>>()
    var error = MutableLiveData<String>()

    fun getAllUser() {
        getAllUserUseCase.execute({
            userList.value = it
        }, {
            error.value = it.message
        })
    }
}