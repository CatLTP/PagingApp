package com.example.pagingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.pagingapp.databinding.SingleMovieItemBinding
import com.example.pagingapp.model.Movie

class MoviesAdapter(
    private val diffCallback: DiffUtil.ItemCallback<Movie>,
    private val glide : RequestManager
) : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(diffCallback)
{

     object item {
        val LOADING_ITEM = 0
        val MOVIE_ITEM = 1
    }


    class MovieViewHolder(val movieItemBinding: SingleMovieItemBinding)
        : RecyclerView.ViewHolder(movieItemBinding.root) {}

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = getItem(position)

        if (currentMovie != null) {
            glide.load("https://image.tmdb.org/t/p/w500"+ currentMovie.posterPath)
                .into(holder.movieItemBinding.imageViewMovie)

            holder.movieItemBinding.textViewRating.text = currentMovie.voteAverage.toString()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount)
            item.MOVIE_ITEM
        else item.LOADING_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(SingleMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }
}