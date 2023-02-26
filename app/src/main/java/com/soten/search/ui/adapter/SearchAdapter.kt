package com.soten.search.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class SearchAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<SearchViewHolder>() {

    private val queries = mutableListOf<String>()

    fun setItems(queries: List<String>) {
        this.queries.clear()
        this.queries.addAll(queries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(queries[position])
    }

    override fun getItemCount(): Int = queries.size
}