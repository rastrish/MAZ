package com.zerone.maz.data

import com.zerone.maz.data.model.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MuzNetworkService {

    @GET(EndPoints.FLICKR)
    suspend fun getMovies(
        @Query("api_key") apikey: String
    ) : MoviesModel
}