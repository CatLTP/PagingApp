package com.example.pagingapp.model

import com.google.gson.annotations.SerializedName

class MovieResponse {

    @SerializedName("page")
    var page : Int = 0

    @SerializedName("results")
    lateinit var results : List<Movie>

    @SerializedName("total_results")
    var totalResults : Int = 0

    @SerializedName("total_pages")
    var totalPages : Int = 0
}