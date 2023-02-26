package com.soten.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soten.search.databinding.ItemRecordBinding

class SearchViewHolder(
    private val binding: ItemRecordBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(query: String) {
        binding.queryTextView.text = query

        binding.root.setOnClickListener {
            onClick.invoke(query)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (String) -> Unit): SearchViewHolder {
            return SearchViewHolder(
                ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onClick
            )
        }
    }
}