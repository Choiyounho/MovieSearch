package com.soten.search.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soten.search.domain.MovieDomain

class MovieAdapter(
    private val onClick: (MovieDomain) -> Unit
) : ListAdapter<MovieDomain, MovieViewHolder>(movieDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val movieDiffer = object : DiffUtil.ItemCallback<MovieDomain>() {
            override fun areItemsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}