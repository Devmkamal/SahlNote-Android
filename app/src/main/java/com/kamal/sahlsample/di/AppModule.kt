package com.kamal.sahlsample.di

import android.content.SharedPreferences
import com.kamal.sahlsample.BuildConfig
import com.kamal.sahlsample.data.service.AuthInterceptor
import com.kamal.sahlsample.data.service.ErrorInterceptor
import com.kamal.sahlsample.data.service.NotesApiService
import com.kamal.sahlsample.data.source.NotesRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {


    //-------------------------------> services <------------------------------\\
    @Singleton
    @Provides
    fun provideNotesService(
        @NoteApi okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = provideService(
        okhttpClient,
        converterFactory,
        BuildConfig.BASE_URL,
        NotesApiService::class.java
    )

    @Singleton
    @Provides
    fun provideNotesRemoteDataSource(notesApiService: NotesApiService) =
        NotesRemoteDataSource(
            notesApiService
        )


    @Singleton
    @Provides
    fun provideAuthInterceptor(sharedPreferences: SharedPreferences, json: Json) =
        AuthInterceptor(
            sharedPreferences, json
        )


    @NoteApi
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient, authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return upstreamClient.newBuilder().callTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(ErrorInterceptor())
            .build()
    }

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        baseUrl: String,
        clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory, baseUrl).create(clazz)
    }
}
