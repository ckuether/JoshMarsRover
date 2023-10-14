package com.example.joshmarsrover.di

import com.example.joshmarsrover.api.RoversApiService
import com.example.joshmarsrover.api.RoversDeserializer
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(TypeToken.getParameterized(List::class.java, Rover::class.java).type, RoversDeserializer())
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: Converter.Factory): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideRoverApiService(retrofit: Retrofit): RoversApiService {
        return retrofit.create(RoversApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoversRepository(apiService: RoversApiService): RoversRepository {
        return RoversRepositoryImpl(apiService)
    }
}