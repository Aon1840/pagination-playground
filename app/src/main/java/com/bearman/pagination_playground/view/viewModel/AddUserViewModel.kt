package com.bearman.pagination_playground.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bearman.pagination_playground.data.room.entity.UserEntity
import com.bearman.pagination_playground.domain.usecase.AddUserUseCase
import javax.inject.Inject

class AddUserViewModel
@Inject constructor(
    private val addUserViewModel: AddUserUseCase,
) : ViewModel() {

    var message = MutableLiveData<String>()
    var isSuccess = MutableLiveData<Boolean>()

    fun addUser(userEntity: UserEntity) {
        addUserViewModel.execute({
            message.value = "Add Success"
            isSuccess.value = true
        }, {
            message.value = it.message
            isSuccess.value = false
        }, params = AddUserUseCase.Param(userEntity))
    }
}