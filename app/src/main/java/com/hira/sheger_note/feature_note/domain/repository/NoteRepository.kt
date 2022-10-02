package com.hira.sheger_note.feature_note.domain.repository

import com.hira.sheger_note.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(Id:Int):Note?
    suspend fun insertNote(note:Note)
    suspend fun deleteNote(note: Note)
}