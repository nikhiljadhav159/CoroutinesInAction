package com.example.demo.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.BR
import com.example.demo.databinding.UserListItemBinding

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    private var userNameList: List<String?> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userListItemBinding = UserListItemBinding.inflate(LayoutInflater.from(parent.context))

        return UserViewHolder(userListItemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(userNameList[position])

    override fun getItemCount(): Int = userNameList.size

    @SuppressLint("NotifyDataSetChanged")
    fun bindUserNameList(userList: List<String>?){
        this.userNameList = userList ?: emptyList()
        notifyDataSetChanged()
    }

    class UserViewHolder(private val itemViewBindings: ViewDataBinding) : RecyclerView.ViewHolder(itemViewBindings.root) {

        fun bind(userName: String?) {
            itemViewBindings.setVariable(BR.userName, userName)
        }
  }
}