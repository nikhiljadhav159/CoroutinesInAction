package com.example.demo.main.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapters {
    @JvmStatic
        @BindingAdapter("bindUserNames")
        fun RecyclerView.bindUserNames(userNames: List<String>? = null) {
            getOrCreateAdapter(this).bindUserNameList(userNames)
        }
        private fun getOrCreateAdapter(recyclerView: RecyclerView): UserListAdapter {
            return if (recyclerView.adapter != null && recyclerView.adapter is UserListAdapter) {
                recyclerView.adapter as UserListAdapter
            } else {
                val userListAdapter = UserListAdapter()
                recyclerView.adapter = userListAdapter
                userListAdapter
            }
        }
    }
