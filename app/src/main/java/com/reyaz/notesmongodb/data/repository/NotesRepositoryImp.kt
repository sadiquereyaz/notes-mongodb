package com.reyaz.notesmongodb.data.repository

import com.reyaz.notesmongodb.common.util.Resource
import com.reyaz.notesmongodb.data.remote.NotesApi
import com.reyaz.notesmongodb.domain.models.Notes
import com.reyaz.notesmongodb.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotesRepositoryImp @Inject constructor(
    val notesApi: NotesApi
) : NotesRepository {
    override suspend fun saveNotes(notes: Notes): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        val status: Int = notesApi.saveNote(notes).code()

        try {
            if (status == 201)
                emit(Resource.Success("Notes saved"))
            else
                emit(Resource.Error("Unable to save notes"))
        } catch (e:Exception){
            emit(Resource.Error(e.message))
        }
    }

    override suspend fun getNotes(): Flow<Resource<List<Notes>>> = flow {
        val status = notesApi.getNotes().code()
        try {
            val notesList = notesApi.getNotes().body()
            if (status == 200)
                emit(Resource.Success(notesList))
            else
                emit(Resource.Error("Unable to save notes"))
        } catch (e:Exception){
            emit(Resource.Error(e.message))
        }
    }

}