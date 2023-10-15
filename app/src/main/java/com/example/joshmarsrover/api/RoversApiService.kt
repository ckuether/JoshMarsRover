package com.example.joshmarsrover.api

import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.data.model.Rover
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RoversApiService {

    companion object {
        const val BASE_ROVER_URL = "mars-photos/api/v1/rovers/"
    }

    @GET(BASE_ROVER_URL)
    fun getRovers(@Query("api_key") apiKey: String): Call<List<Rover>>

    @GET("$BASE_ROVER_URL/{name}/photos")
    fun getRoverPhotos(@Path("name") name: String, @Query("earth_date") date: String, @Query("api_key") apiKey: String): Call<List<Photo>>
}