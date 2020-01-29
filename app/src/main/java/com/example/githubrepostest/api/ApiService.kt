package com.example.githubrepostest.api

import com.example.githubrepostest.api.response.RepoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/repositories")
    fun getRepositoriesList():Call<List<RepoResponse>>



    companion object{
        val baseUrl = "https://api.github.com"

        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val api:ApiService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .build()

            retrofit.create(ApiService::class.java)
        }
    }

}