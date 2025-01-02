package com.reyaz.notesmongodb.domain.repository

import com.reyaz.notesmongodb.common.util.Resource
import com.reyaz.notesmongodb.domain.models.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun saveNotes(notes: Notes): Flow<Resource<String>>
    suspend fun getNotes(): Flow<Resource<List<Notes>>>
}

