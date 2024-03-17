package com.example.netfilxcloneapp.presentation.screens.person.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.person_models_domain.ResultDomain
import com.example.netfilxcloneapp.R
import com.example.netfilxcloneapp.presentation.screens.home.adapter.ActorsItemDiffUtil

class ActorsAdapter :
    ListAdapter<ResultDomain, ActorsAdapter.ActorViewHolder>(ActorsItemDiffUtil()) {

    inner class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val actorPoster: ImageView = view.findViewById(R.id.actorPoster)
        private val actorName: TextView = view.findViewById(R.id.actorRealName)
        private val characterName: TextView = view.findViewById(R.id.ageActor)

        fun bind(model: ResultDomain) {
            Glide.with(itemView.context)
                .load(model.profilePath)
                .into(actorPoster)

            val genderString = if (model.gender == 1) {
                "Female"
            } else if (model.gender == 2) {
                "Male"
            } else {
                "Unknown"
            }

                actorName.text = model.name
            characterName.text = genderString

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_actors_items, parent, false)
        return ActorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}