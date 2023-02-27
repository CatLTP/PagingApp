package com.example.pagingapp.model

import com.google.gson.annotations.SerializedName


// API KEY : 62bc16215f48f9346d8c8051cd8627e2

class Movie {

    @SerializedName("id")
    var id : String = ""

    @SerializedName("poster_path")
    var posterPath : String = ""

    @SerializedName("vote_average")
    var voteAverage : Double = 0.0


    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other == this.javaClass) return true
        return false

    }

}