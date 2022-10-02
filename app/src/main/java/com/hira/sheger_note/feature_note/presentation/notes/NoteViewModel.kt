package com.hira.sheger_note.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hira.sheger_note.feature_note.domain.model.Note
import com.hira.sheger_note.feature_note.domain.use_case.NoteUseCase
import com.hira.sheger_note.feature_note.domain.util.NoteOrder
import com.hira.sheger_note.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCase:NoteUseCase
):ViewModel() {



    private val _state = mutableStateOf(NoteStates())
    val state: State<NoteStates> = _state
    private var recentlyDelatedNote:Note?=null

    private var getNotesJob: Job?=null
    init{
        getsNote(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event) {
            is NotesEvent.Order-> {
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }
                getsNote(event.noteOrder)

            }
            is NotesEvent.DeleteNote->{
                viewModelScope.launch{
                    noteUseCase.deleteNote(event.note)
                    recentlyDelatedNote=event.note

                }


            }
            is NotesEvent.RestoreNote->{
                viewModelScope.launch {
                    noteUseCase.addNote(recentlyDelatedNote?:return@launch)
                    recentlyDelatedNote=null
                }

            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )



            }

        }
    }
    private fun getsNote(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob=noteUseCase.getNotes(noteOrder).onEach { notes->
            _state.value=state.value.copy(
                notes=notes,
                noteOrder=noteOrder
            )
        }.launchIn(viewModelScope)
    }
}