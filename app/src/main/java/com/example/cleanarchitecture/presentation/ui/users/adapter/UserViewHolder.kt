package com.example.cleanarchitecture.presentation.ui.users.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.example.cleanarchitecture.databinding.ItemUserBinding
import com.example.cleanarchitecture.domain.model.User

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            image.load(user.avatarUrl) {
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            textName.text = user.login
        }
    }
}