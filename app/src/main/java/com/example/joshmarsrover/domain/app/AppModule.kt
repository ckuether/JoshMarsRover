package com.example.joshmarsrover.domain.app

import com.example.joshmarsrover.BuildConfig
import com.example.joshmarsrover.api.PhotosDeserializer
import com.example.joshmarsrover.api.RoversApiService
import com.example.joshmarsrover.api.RoversDeserializer
import com.example.joshmarsrover.common.Contstants
import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.data.repository.RoversRepositoryImpl
import com.example.joshmarsrover.domain.repository.RoversRepository
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @NASA_API_KEY
    @Provides
    @Singleton
//    fun nasaAPIKey(): String = BuildConfig.NASA_API_KEY
    fun nasaAPIKey(): String = Contstants.API_KEY
    //TODO:

    @Provides
    @Singleton
    fun gsonConverterFactory(): Converter.Factory{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(TypeToken.getParameterized(List::class.java, Rover::class.java).type, RoversDeserializer())
        gsonBuilder.registerTypeAdapter(TypeToken.getParameterized(List::class.java, Photo::class.java).type, PhotosDeserializer())
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Provides
    @Singleton
    fun retrofit(converterFactory: Converter.Factory): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun roverApiService(retrofit: Retrofit): RoversApiService {
        return retrofit.create(RoversApiService::class.java)
    }

    @Provides
    @Singleton
    fun roversRepository(apiService: RoversApiService, @NASA_API_KEY apiKey: String): RoversRepository {
        return RoversRepositoryImpl(apiService, apiKey)
    }
}

@Retention(AnnotationRetention.SOURCE)
@Qualifier
annotation class NASA_API_KEY