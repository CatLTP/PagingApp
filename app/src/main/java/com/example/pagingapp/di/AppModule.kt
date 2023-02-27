package com.example.pagingapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.pagingapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getGilde(@ApplicationContext context: Context) : RequestManager {
        return Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions()
                .error(R.drawable.ic_image)
                .placeholder(R.drawable.ic_image))
    }
}