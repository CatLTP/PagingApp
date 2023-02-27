package com.example.pagingapp.api

import com.example.pagingapp.model.MovieResponse
import com.example.pagingapp.util.Utils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object APIClient {

    var apiInterface : APIInterface
    private set

    init {
        val client = OkHttpClient.Builder()

        client.addInterceptor {
            val original = it.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Utils.key)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)
            val request = requestBuilder.build()
            return@addInterceptor it.proceed(request)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(Utils.baseURL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(APIInterface::class.java)
    }


    interface APIInterface {
        @GET("movie/popular")
        suspend fun getMoviesByPage(@Query("page") page : Int) : MovieResponse
    }
}