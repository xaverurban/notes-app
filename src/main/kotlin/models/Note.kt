package models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note(
    var noteTitle: String,
    var notePriority: Int,
    var noteCategory: String,
    var isNoteArchived: Boolean,
    var creationTime: LocalDateTime = LocalDateTime.now(),
    var updateTime: LocalDateTime = LocalDateTime.now()

) {
    constructor() : this("", 0, "", false)

    override fun toString(): String {
        val priorityString = when (notePriority) {
            1 -> "Low"
            2 -> "Medium/Low"
            3 -> "Medium"
            4 -> "Medium/High"
            5 -> "High"
            else -> "ERROR"
        }

        val formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

        return """
        ${"\u001B[34m"}+------------------------------+
         Title: ${"\u001B[35m"}$noteTitle${"\u001B[34m"}
         Priority: ${"\u001B[35m"}$priorityString${"\u001B[34m"}
         Category: ${"\u001B[35m"}$noteCategory${"\u001B[34m"}
         Archived: ${"\u001B[35m"}$isNoteArchived${"\u001B[34m"}
         Creation Time: ${"\u001B[35m"}${creationTime.format(formatTime)}${"\u001B[34m"}
         Last Updated: ${"\u001B[35m"}${updateTime.format(formatTime)}${"\u001B[34m"}
        ${"\u001B[34m"}+------------------------------+ 
    """.trimIndent()
    }
}