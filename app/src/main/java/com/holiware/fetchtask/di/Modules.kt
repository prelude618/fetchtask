package com.holiware.fetchtask.di

import com.holiware.fetchtask.data.ItemApi
import com.holiware.fetchtask.data.ItemRepositoryImpl
import com.holiware.fetchtask.data.ItemRepository
import com.holiware.fetchtask.viewmodel.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {
    viewModel {
        ItemViewModel(get())
    }
    single<ItemRepository> {
        ItemRepositoryImpl(get())
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .build()
            .create(ItemApi::class.java)
    }
}