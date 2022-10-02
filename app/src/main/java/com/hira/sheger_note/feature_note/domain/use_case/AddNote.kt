package com.hira.sheger_note.feature_note.domain.use_case
import com.hira.sheger_note.feature_note.domain.model.InvalidNoteException
import com.hira.sheger_note.feature_note.domain.model.Note
import com.hira.sheger_note.feature_note.domain.repository.NoteRepository
class AddNote(private val repository: NoteRepository) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note:Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("the Title of the note can't be empty. ")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("the Content of the note can't be empty. ")
        }
        repository.insertNote(note)
    }
}