package com.bearman.pagination_playground.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bearman.pagination_playground.R
import com.bearman.pagination_playground.data.room.entity.UserEntity
import com.bearman.pagination_playground.view.viewModel.AddUserViewModel
import com.bearman.pagination_playground.view.viewModel.GetAllUserViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var addUserViewModel: AddUserViewModel
    private lateinit var getAllUserViewModel: GetAllUserViewModel

    lateinit var etName: EditText
    lateinit var etPhone: EditText
    lateinit var btAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addUserViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddUserViewModel::class.java)
        getAllUserViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GetAllUserViewModel::class.java)
        getAllUserViewModel.getAllUser()
        initView()
        handleAddUser()
        observer()
    }

    private fun initView() {
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        btAdd = findViewById(R.id.btAdd)
    }

    private fun observer() {
        getAllUserViewModel.userList.observe(this, {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })

        getAllUserViewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        addUserViewModel.message.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun handleAddUser() {
        btAdd.setOnClickListener {
            addUserViewModel.addUser(
                UserEntity(
                    username = etName.text.toString(),
                    mobile = etPhone.text.toString()
                )
            )
        }
    }
}