package com.bearman.pagination_playground.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bearman.pagination_playground.R
import com.bearman.pagination_playground.data.room.entity.UserEntity
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter(
    var users: ArrayList<UserEntity>
): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateUsers(newUsers: List<UserEntity>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(user: UserEntity) {
            itemView.rootView.apply {
                userId.text = "ID: ${user.id}"
                userName.text = "Name: ${user.username}"
                userMobile.text = "Mobile: ${user.mobile}"
            }
        }
    }
}