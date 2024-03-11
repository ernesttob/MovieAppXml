package com.example.netfilxcloneapp.presentation.screens.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.domain.models.MovieDomainModel
import com.example.netfilxcloneapp.R

class MoviesItemAdapter : ListAdapter<MovieDomainModel, MoviesItemAdapter.MoviesItemViewHolder>(
    MovieItemDiffUtil()
) {

    inner class MoviesItemViewHolder(
        private val view: View
    ) : ViewHolder(view) {
        private val moviePoster = view.findViewById<ImageView>(R.id.moviePoster)

        fun bind(model: MovieDomainModel) {
            Glide
                .with(itemView.context)
                .load(model.backdropPath)
                .into(moviePoster)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesItemViewHolder {
        val moviePeekingItem = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_peeking_item,
            parent,
            false
        )
        return MoviesItemViewHolder(moviePeekingItem)
    }

    override fun onBindViewHolder(holder: MoviesItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}