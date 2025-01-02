package com.reyaz.notesmongodb.presentation.di

import com.reyaz.notesmongodb.data.remote.NotesApi
import com.reyaz.notesmongodb.data.repository.NotesRepositoryImp
import com.reyaz.notesmongodb.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideNotesApi(): NotesApi {
        return Retrofit.Builder()
            .baseUrl("http://10.57.3.61:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotesApi::class.java)
    }

    @Provides
    fun provideNotesRepository(notesApi: NotesApi): NotesRepository {
        return NotesRepositoryImp(notesApi)
    }
}

