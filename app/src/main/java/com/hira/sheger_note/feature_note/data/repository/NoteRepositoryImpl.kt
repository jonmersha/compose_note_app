package com.hira.sheger_note.feature_note.data.repository

import com.hira.sheger_note.feature_note.data.data_source.NoteDao
import com.hira.sheger_note.feature_note.domain.model.Note
import com.hira.sheger_note.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao:NoteDao
):NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
       return dao.getNotes()
    }

    override suspend fun getNoteById(Id: Int): Note? {
        return dao.getNotesById(Id)


    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)

    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)

    }
}