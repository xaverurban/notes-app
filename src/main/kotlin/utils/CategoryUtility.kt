package utils

object CategoryUtility {


    @JvmStatic
    val categories = setOf ("Home", "College","Pool","Gym","Work")

    @JvmStatic
    fun isValidCategory(categoryToCheck: String?): Boolean {
        for (category in categories) {
            if (category.equals(categoryToCheck, ignoreCase = true)) {
                return true
            }
        }
        return false
    }


}