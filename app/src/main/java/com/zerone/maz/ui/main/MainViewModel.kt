package com.zerone.maz.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zerone.maz.MuzRepository
import com.zerone.maz.data.model.MoviesModel
import com.zerone.maz.data.MovieData
import com.zerone.maz.ui.base.BaseViewModel
import com.zerone.maz.util.Resource
import com.zerone.maz.util.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val medtrailRepository: MuzRepository) : BaseViewModel() {


    val photos = MutableLiveData<Resource<MoviesModel>>()
    private var url = String()
    private var title = String()
    val temp: ArrayList<MovieData> = ArrayList()
    var list: MutableLiveData<ArrayList<MovieData>> = MutableLiveData()


    // Exception Handler
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Error", throwable?.let { it.message})
        photos.postValue(Resource(Status.ERROR, null))

    }



    // Network Call with Kotlin Corountines

    fun getPhotos() {
        if (checkInternetConnection()) {
            photos.postValue(Resource(Status.LOADING, null))
            CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
               medtrailRepository.getMovies().let {
                   for(imagePath in it.results){
                     url =  "https://image.tmdb.org/t/p/w200"+imagePath.poster_path
                     title =   imagePath.original_title
                       val overview = imagePath.overview
                       temp.add(MovieData(title,url,overview,false))
                   }
                    list.postValue(temp)
                }
            }
            photos.postValue(Resource(Status.SUCCESS,null))
        }
    }

}