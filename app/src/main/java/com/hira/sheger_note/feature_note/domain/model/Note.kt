package com.hira.sheger_note.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hira.sheger_note.ui.theme.*

@Entity
data class Note(
    @PrimaryKey val id:Int?=null,
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int
){
    companion object{
        val nodeColors= listOf(RedOrange,LightGreen,Violet,BabyBlue,RedPink)
    }
}
class InvalidNoteException(message:String):Exception(message)