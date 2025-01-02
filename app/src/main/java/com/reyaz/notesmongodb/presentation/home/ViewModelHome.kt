package com.reyaz.notesmongodb.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyaz.notesmongodb.common.util.Resource
import com.reyaz.notesmongodb.domain.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelHome @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {
    init {
        getNotes()
    }

    private val _stateNote = MutableStateFlow(StateHomeScreen())
    val stateNote = _stateNote.asStateFlow()


    private fun getNotes() {
        viewModelScope.launch {
            notesRepository.getNotes().collect { resource ->
                when (resource) {

                    is Resource.Loading -> {
                        _stateNote.value = stateNote.value.copy(gettingNotes = true)
                    }

                    is Resource.Success -> {
                        _stateNote.value = stateNote.value.copy(
                            gettingNotes = false,
                            fetchedNotes = resource.data
                        )
                    }

                    is Resource.Error -> {
                        _stateNote.value = stateNote.value.copy(
                            gettingNotes = false,
                            error = resource.message.toString()
                        )
                    }
                }
            }
        }

    }
}