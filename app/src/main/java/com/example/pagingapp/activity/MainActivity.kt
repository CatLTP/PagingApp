package com.example.pagingapp.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.pagingapp.R
import com.example.pagingapp.adapter.MoviesAdapter
import com.example.pagingapp.adapter.MoviesLoadStateAdapter
import com.example.pagingapp.databinding.ActivityMainBinding
import com.example.pagingapp.model.Movie
import com.example.pagingapp.util.GridSpace
import com.example.pagingapp.util.MovieComparator
import com.example.pagingapp.util.Utils
import com.example.pagingapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.subscribe
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel : MovieViewModel
    private lateinit var binding : ActivityMainBinding
    private lateinit var moviesAdapter : MoviesAdapter

    @Inject
    lateinit var requestManager : RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Utils.key == null || Utils.key.isEmpty()) {
            Toast.makeText(this,"Error in API key",Toast.LENGTH_SHORT).show()
        }

        moviesAdapter = MoviesAdapter(MovieComparator(), requestManager)

        mainActivityViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        initRecyclerViewAndAdapter()

        mainActivityViewModel.moviePagingDataFlowable.onEach { pagingData: PagingData<Movie> ->
            moviesAdapter.submitData(lifecycle, pagingData) }
            .launchIn(CoroutineScope(Dispatchers.IO))
    }

    private fun initRecyclerViewAndAdapter() {

        val gridLayoutManager = GridLayoutManager(this,2)
        binding.recyclerViewMovies.layoutManager = gridLayoutManager
        binding.recyclerViewMovies.addItemDecoration(GridSpace(2,20,true))
        binding.recyclerViewMovies.adapter = moviesAdapter.withLoadStateFooter(
            MoviesLoadStateAdapter {
                moviesAdapter.retry()
            })

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if (moviesAdapter.getItemViewType(position) == MoviesAdapter.item.LOADING_ITEM) 1
                        else 2
            }
        }
    }
}