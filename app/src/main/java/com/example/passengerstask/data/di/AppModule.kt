package com.example.passengerstask.data.di

import android.content.Context
import com.example.passengerstask.BuildConfig
import com.example.passengerstask.data.locale.AirLineRoomDataBase
import com.example.passengerstask.data.retrofit.AirLineApiHelper
import com.example.passengerstask.data.retrofit.AirLineApiService
import com.example.passengerstask.data.retrofit.AirLineHelperImplementation

import com.example.passengerstask.data.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient () = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL : String) : Retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(AirLineApiService::class.java)


    @Singleton
    @Provides
    fun provideJobsApiHelper(jobsApiHelperImplementation: AirLineHelperImplementation) : AirLineApiHelper = jobsApiHelperImplementation

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AirLineRoomDataBase.getDataBase(appContext)


    @Provides
    fun provideJobDao(db: AirLineRoomDataBase) = db.airLineDao()


}