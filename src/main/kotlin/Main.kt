import controllers.NoteAPI
import models.Note
import mu.KotlinLogging
import persistence.JSONSerializer
import persistence.XMLSerializer
import persistence.YAMLSerializer
import utils.CategoryUtility
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.ValidateInput.readValidCategory
import utils.ValidateInput.readValidPriority
import java.io.File
import java.lang.System.exit
import kotlin.system.exitProcess
import org.fusesource.jansi.AnsiConsole

//private val noteAPI = NoteAPI()
//private val noteAPI = NoteAPI(XMLSerializer(File("notes.xml")))
//private val noteAPI = NoteAPI(JSONSerializer(File("notes.json")))
private val noteAPI = NoteAPI(YAMLSerializer(File("notes.yaml")))

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    runMenu()

}

fun mainMenu(): Int {
    return readNextInt(""" 
         > ${"\u001B[34m"}----------------------------------
         > |        ${"\u001B[35m"}Note Keeper App         ${"\u001B[34m"}|
         > ----------------------------------
         > | ${"\u001B[32m"}NOTE MENU                      ${"\u001B[34m"}|
         > |   ${"\u001B[36m"}1) ${"\u001B[32m"}📝 Add a note${"\u001B[34m"}             |          
         > |   ${"\u001B[36m"}2) ${"\u001B[32m"}📋 List all notes${"\u001B[34m"}         |    
         > |   ${"\u001B[36m"}3) ${"\u001B[32m"}🖋️ Update a note${"\u001B[34m"}          | 
         > |   ${"\u001B[36m"}4) ${"\u001B[32m"}🗑️ Delete a note${"\u001B[34m"}          | 
         > |   ${"\u001B[36m"}5) ${"\u001B[32m"}📦 Archive Note${"\u001B[34m"}           | 
         > |   ${"\u001B[36m"}6) ${"\u001B[32m"}🔍 Search Note${"\u001B[34m"}            |  
         > ----------------------------------                           
         > |   ${"\u001B[36m"}10) ${"\u001B[32m"}💾 Save Notes${"\u001B[34m"}            |         
         > |   ${"\u001B[36m"}11) ${"\u001B[32m"}📁 Load Notes${"\u001B[34m"}            |   
         > ----------------------------------
         > |   ${"\u001B[36m"}0) ${"\u001B[32m"}🚪 Exit${"\u001B[34m"}                   |      
         > ----------------------------------
         > ==>> """.trimMargin(">"))
}




fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1  -> addNote()
            2  -> listNotes()
            3  -> updateNote()
            4  -> deleteNote()
            5 -> archiveNote()
            6 -> searchNotes()
            10 ->save()
            11 ->load()
            0  -> exitApp()
            else -> println("Invalid option entered: $option")

        }
    } while (true)
}
fun addNote(){
    logger.info { "addNote() function invoked" }
    val noteTitle = readNextLine("Enter a title for the note: ")
    val notePriority = readValidPriority("Enter a priority (1-low, 2, 3, 4, 5-high): ")
    val noteCategory = readValidCategory("Enter a category for the note ${CategoryUtility.categories}:")
    val isAdded = noteAPI.add(Note(noteTitle, notePriority, noteCategory, false))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}


    fun listNotes() {
        if (noteAPI.numberOfNotes() > 0) {
            val option = readNextInt(
                """
                  > --------------------------------
                  > |   1) View all notes          |
                  > |   2) View active notes       |
                  > |   3) View archived notes     |
                  > --------------------------------
         > ==>> """.trimMargin(">"))

            when (option) {
                1 -> listAllNotes();
                2 -> listActiveNotes();
                3 -> listArchivedNotes();
                else -> println("Invalid option entered: $option");
            }
        } else {
            println("Option Invalid - No notes stored currently");
        }
    }



fun updateNote() {
    //logger.info { "updateNotes() function invoked" }
    listNotes()
    if (noteAPI.numberOfNotes() > 0) {
        //only ask the user to choose the note if notes exist
        val indexToUpdate = readNextInt("Enter the index of the note to update: ")
        if (noteAPI.isValidIndex(indexToUpdate)) {
            val noteTitle = readNextLine("Enter a title for the note: ")
            val notePriority = readValidPriority("Enter a priority (1-low, 2, 3, 4, 5-high): ")
            val noteCategory = readValidCategory("Enter a category for the note ${CategoryUtility.categories}: ")

            //pass the index of the note and the new note details to NoteAPI for updating and check for success.
            if (noteAPI.updateNote(indexToUpdate, Note(noteTitle, notePriority, noteCategory, false))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no notes for this index number")
        }
    }
}


fun deleteNote(){
    //logger.info { "deleteNotes() function invoked" }
    listNotes()
    if (noteAPI.numberOfNotes() > 0) {
        //only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the note to delete: ")
        //pass the index of the note to NoteAPI for deleting and check for success.
        val noteToDelete = noteAPI.deleteNote(indexToDelete)
        if (noteToDelete != null) {
            println("Delete Successful! Deleted note: ${noteToDelete.noteTitle}")
        } else {
            println("Delete NOT Successful")
        }
    }

}
fun listActiveNotes() {
    println(noteAPI.listActiveNotes())
}
fun archiveNote() {
    listActiveNotes()
    if (noteAPI.numberOfActiveNotes() > 0) {
        val indexToArchive = readNextInt("Enter index of the note to archive: ")
        if (noteAPI.archiveNote(indexToArchive)) {
            println("Archive was successful")
        } else {
            println("Archive was not successful")
        }
    }
}
fun listAllNotes() {
    println(noteAPI.listAllNotes())
}

fun listArchivedNotes() {
    println(noteAPI.listArchivedNotes())
}
fun searchNotes() {
    val searchTitle = readNextLine("Enter the description to search by: ")
    val searchResults = noteAPI.searchByTitle(searchTitle)
    if (searchResults.isEmpty()) {
        println("No notes found")
    } else {
        println(searchResults)
    }
}

fun exitApp(){
    println("Exiting...bye")
    exitProcess(0)
}
fun save() {
    try {
        noteAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        noteAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}


