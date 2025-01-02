package com.reyaz.notesmongodb.presentation.home

import com.reyaz.notesmongodb.domain.models.Notes

data class StateHomeScreen(
    val gettingNotes:Boolean = false,
    val fetchedNotes: List<Notes>? = emptyList(),   // nullable
    val error: String = ""
)
