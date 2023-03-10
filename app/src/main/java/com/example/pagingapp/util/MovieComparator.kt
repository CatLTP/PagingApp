package com.example.pagingapp.util

import androidx.recyclerview.widget.DiffUtil
import com.example.pagingapp.model.Movie

class MovieComparator : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

}