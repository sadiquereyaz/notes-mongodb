package com.reyaz.notesmongodb.presentation.addnote

import com.reyaz.notesmongodb.domain.models.Notes

sealed class EventAddNoteScreen{
    data class NoteTitle(val title:String) : EventAddNoteScreen()
    data class NoteDescription(val description:String) : EventAddNoteScreen()
    data class NotesLabel(val label:String) : EventAddNoteScreen()
    data class SaveNote(val notes: Notes): EventAddNoteScreen()
}
