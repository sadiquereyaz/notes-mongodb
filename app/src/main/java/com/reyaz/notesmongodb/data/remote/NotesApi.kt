package com.reyaz.notesmongodb.data.remote

import com.reyaz.notesmongodb.common.util.Resource
import com.reyaz.notesmongodb.domain.models.Notes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesApi {
    @POST("/add-note")
    suspend fun saveNote(@Body note: Notes): Response<Notes>

    @GET("/notes")
    suspend fun getNotes(): Response<List<Notes>>
}

