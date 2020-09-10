package com.zerone.maz

import com.zerone.maz.data.MuzNetworkService
import com.zerone.maz.data.model.MoviesModel

class MuzRepository (
    private val medTrailNetworkService: MuzNetworkService
) {


    private  val apiKey = BuildConfig.APKI_KEY

    suspend fun getMovies() : MoviesModel {
       return medTrailNetworkService.getMovies(apiKey)
    }

}