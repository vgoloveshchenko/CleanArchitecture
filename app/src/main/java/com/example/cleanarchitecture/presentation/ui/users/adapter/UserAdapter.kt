package com.example.cleanarchitecture.presentation.ui.users.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.databinding.ItemLoadingBinding
import com.example.cleanarchitecture.databinding.ItemUserBinding
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.presentation.model.PagingData

class UserAdapter(
    context: Context
) : ListAdapter<PagingData<User>, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PagingData.Content -> TYPE_USER
            PagingData.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_USER -> {
                UserViewHolder(
                    binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder(
                    binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> error("Unsupported viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = (getItem(position) as? PagingData.Content)?.data ?: return
        (holder as? UserViewHolder)?.bind(user)
    }

    companion object {

        private const val TYPE_USER = 1
        private const val TYPE_LOADING = 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PagingData<User>>() {
            override fun areItemsTheSame(
                oldItem: PagingData<User>,
                newItem: PagingData<User>
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PagingData<User>,
                newItem: PagingData<User>
            ): Boolean {
                val oldUser = oldItem as? PagingData.Content
                val newUser = newItem as? PagingData.Content
                return oldUser?.data == newUser?.data
            }
        }
    }
}