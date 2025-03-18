package com.holiware.fetchtask.data

import retrofit2.http.GET

interface ItemApi {
    @GET("hiring.json")
    suspend fun getItem(): List<Item>
}