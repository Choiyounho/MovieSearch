package com.soten.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.search.R
import com.soten.search.databinding.ItemMovieBinding
import com.soten.search.domain.MovieDomain

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onClick: (MovieDomain) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieDomain: MovieDomain) {

        binding.thumbnailImageView.load(movieDomain.image) {
            error(R.drawable.baseline_error_24)
        }
        binding.titleTextView.text = HtmlCompat.fromHtml(movieDomain.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.pubDateTextView.text = movieDomain.pubDate
        binding.userRatingTextView.text = movieDomain.userRating

        binding.root.setOnClickListener {
            onClick.invoke(movieDomain)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (MovieDomain) -> Unit): MovieViewHolder {
            return MovieViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick
            )
        }
    }
}