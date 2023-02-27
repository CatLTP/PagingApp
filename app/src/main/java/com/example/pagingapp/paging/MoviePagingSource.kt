package com.example.pagingapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingapp.api.APIClient
import com.example.pagingapp.model.Movie

class MoviePagingSource : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1

            val response = APIClient.apiInterface.getMoviesByPage(page)
            LoadResult.Page(
                data = response.results,
                  if (page == 1)
                      null
                    else page - 1,
                page + 1
            )
        } catch (e : java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}