//package com.hira.sheger_note.feature_note.presentation.notes.components
//
//import android.graphics.Paint.Style
//
//abstract class RichTextValue {
//    /**
//     * Returns styles that are used inside the current selection (or composition)
//     */
//    abstract val currentStyles: Set<Style>
//    abstract val isUndoAvailable: Boolean
//    abstract val isRedoAvailable: Boolean
//
//    abstract fun insertStyle(style: Style): RichTextValue
//    abstract fun clearStyles(vararg styles: Style): RichTextValue
//
//    abstract fun undo(): RichTextValue
//    abstract fun redo(): RichTextValue
//}