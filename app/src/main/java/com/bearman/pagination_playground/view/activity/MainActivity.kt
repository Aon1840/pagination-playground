package com.bearman.pagination_playground.view.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bearman.pagination_playground.R
import com.bearman.pagination_playground.data.room.entity.UserEntity
import com.bearman.pagination_playground.view.adapter.UserListAdapter
import com.bearman.pagination_playground.view.viewModel.AddUserViewModel
import com.bearman.pagination_playground.view.viewModel.GetAllUserViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var addUserViewModel: AddUserViewModel
    private lateinit var getAllUserViewModel: GetAllUserViewModel
    private var userListAdapter = UserListAdapter(arrayListOf())

    lateinit var rvUser: RecyclerView
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
        rvUser = findViewById(R.id.rvUser)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        btAdd = findViewById(R.id.btAdd)

        rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userListAdapter
        }
    }

    private fun observer() {
        getAllUserViewModel.userList.observe(this, {
            userListAdapter.updateUsers(it)
        })

        getAllUserViewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        addUserViewModel.message.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        addUserViewModel.isSuccess.observe(this, {
            if (it) {
                getAllUserViewModel.getAllUser()
            }
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