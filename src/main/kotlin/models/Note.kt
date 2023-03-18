package models



data class Note(
    var noteTitle: String,
    var notePriority: Int,
    var noteCategory: String,
    var isNoteArchived: Boolean
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
        return """
        ${"\u001B[34m"}+------------------------------+
         Title: ${"\u001B[35m"}$noteTitle${"\u001B[34m"}
         Priority: ${"\u001B[35m"}$priorityString${"\u001B[34m"}
         Category: ${"\u001B[35m"}$noteCategory${"\u001B[34m"}
         Archived: ${"\u001B[35m"}$isNoteArchived${"\u001B[34m"}
        ${"\u001B[34m"}+------------------------------+ 
    """.trimIndent()
    }
}