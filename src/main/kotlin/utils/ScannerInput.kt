package utils

import java.lang.NumberFormatException
import java.util.*

/**
 * This class provides methods for the robust handling of I/O using Scanner.
 * It creates a new Scanner object for each read from the user, thereby eliminating the Scanner bug
 * (where the buffers don't flush correctly after an int read).
 *
 * The methods also parse the numeric data entered to ensure it is correct. If it isn't correct,
 * the user is prompted to enter it again.
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @since 1.0
 */

object ScannerInput {
    /**
     * Read an int from the user.
     * If the entered data isn't actually an int the user is prompted again to enter the int.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as an int.
     */
    @JvmStatic
    fun readNextInt(prompt: String?): Int {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toInt()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }

    /**
     * Read a double from the user.  If the entered data isn't actually a double,
     * the user is prompted again to enter the double.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as a double.
     */
    @JvmStatic
    fun readNextDouble(prompt: String?): Double {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toDouble()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }

    /**
     * Read a line of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The String read from the user.
     */
    @JvmStatic
    fun readNextLine(prompt: String?): String {
        print(prompt)
        return Scanner(System.`in`).nextLine()
    }

    /**
     * Read a single character of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The char read from the user.
     */
    @JvmStatic
    fun readNextChar(prompt: String?): Char {
        print(prompt)
        return Scanner(System.`in`).next()[0]
    }
}
