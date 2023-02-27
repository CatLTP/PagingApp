package com.example.pagingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingapp.R
import com.example.pagingapp.databinding.LoadStateItemBinding

class MoviesLoadStateAdapter(
    private val mRetryCallback : View.OnClickListener
) : LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(
        parent : ViewGroup,
        retryCallback : View.OnClickListener
    ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.load_state_item,parent,false)) {

        private var mProgressBar : ProgressBar
        private var mErrorMsg : TextView
        private var mRetry : Button

        init {
            val binding = LoadStateItemBinding.bind(itemView)
            mProgressBar = binding.progressBar
            mErrorMsg = binding.errorMsg
            mRetry = binding.retryButton
            mRetry.setOnClickListener(retryCallback)
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                mErrorMsg.text = loadState.error.localizedMessage
            }

            mProgressBar.visibility = if (loadState is LoadState.Loading) View.VISIBLE
                                      else View.GONE

            mRetry.visibility = if (loadState is LoadState.Error) View.VISIBLE
                                else View.GONE

            mErrorMsg.visibility = if (loadState is LoadState.Error) View.VISIBLE
                                        else View.GONE
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(parent, mRetryCallback)
    }


}