package com.example.netfilxcloneapp.presentation.screens.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.MovieDomainModel

class MovieItemDiffUtil : DiffUtil.ItemCallback<MovieDomainModel>() {

    override fun areItemsTheSame(
        oldItem: MovieDomainModel,
        newItem: MovieDomainModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MovieDomainModel,
        newItem: MovieDomainModel
    ): Boolean = oldItem == newItem

}