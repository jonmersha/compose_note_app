package com.hira.sheger_note.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.unit.dp
import com.hira.sheger_note.feature_note.domain.util.NoteOrder
import com.hira.sheger_note.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier=Modifier,
    noteOrder: NoteOrder=NoteOrder.Date(OrderType.Descending),
    onOrderChange:(NoteOrder)->Unit

){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteOrder is NoteOrder.Title,
                onSelect = {
                    onOrderChange(noteOrder.copy(OrderType.Ascending))
                }

            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = noteOrder is NoteOrder.Title,
                onSelect = {
                    onOrderChange(noteOrder.copy(OrderType.Descending))
                }

            )
            Spacer(modifier = Modifier.width(8.dp))



        }

    }
}