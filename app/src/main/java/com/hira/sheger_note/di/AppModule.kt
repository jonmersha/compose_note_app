package com.hira.sheger_note.di

import android.app.Application
import androidx.room.Room
import com.hira.sheger_note.feature_note.data.data_source.NoteDatabase
import com.hira.sheger_note.feature_note.data.repository.NoteRepositoryImpl
import com.hira.sheger_note.feature_note.domain.repository.NoteRepository
import com.hira.sheger_note.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDataBase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository):NoteUseCase{
        return NoteUseCase(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)


        )
    }

}