package com.zerone.maz.di

import com.zerone.maz.*
import com.zerone.maz.data.MuzNetworkService
import com.zerone.maz.data.Networking
import com.zerone.maz.ui.detailed.DetailViewModel
import com.zerone.maz.ui.main.MainViewModel
import com.zerone.maz.ui.main.MovieAdapter
import com.zerone.maz.util.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    fun provideMedtrailNetworkService(): MuzNetworkService = Networking.createNetworkCall()

    single { provideMedtrailNetworkService() }
    single { MuzRepository(get()) }
    single { MovieAdapter() }
    single { NetworkHelper(androidContext()) }

    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailViewModel()
    }

}