package com.example.pagingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.pagingapp.model.Movie
import com.example.pagingapp.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {

    var moviePagingDataFlowable : Flow<PagingData<Movie>>

    init {
        val moviePagingSource = MoviePagingSource()

        val pager = Pager(
            PagingConfig(
                20,
                20,
                false,
                20,
                20*499
            ),null
        ) { moviePagingSource }

        moviePagingDataFlowable = pager.flow
        moviePagingDataFlowable.cachedIn(viewModelScope)
    }


}