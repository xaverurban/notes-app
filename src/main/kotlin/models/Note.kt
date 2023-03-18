package models

data class Note(
    var noteTitle: String,

    var notePriority: Int,
    var noteCategory: String,
    var isNoteArchived :Boolean)

{
    constructor() : this("", 0, "", false)
}