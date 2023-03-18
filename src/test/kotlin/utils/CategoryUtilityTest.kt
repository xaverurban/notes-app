package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.CategoryUtility.categories
import utils.CategoryUtility.isValidCategory

internal class CategoryUtilityTest {


    @Test
    fun categoriesReturnsFullCategoriesSet(){
        Assertions.assertEquals(5, categories.size)
        Assertions.assertTrue(categories.contains("Gym"))
        Assertions.assertTrue(categories.contains("Home"))
        Assertions.assertTrue(categories.contains("Work"))
        Assertions.assertFalse(categories.contains(""))
    }

    @Test
    fun isValidCategoryTrueWhenCategoryExists(){
        Assertions.assertTrue(isValidCategory("Gym"))
        Assertions.assertTrue(isValidCategory("gym"))
        Assertions.assertTrue(isValidCategory("home"))
        Assertions.assertTrue(isValidCategory("Home"))
        Assertions.assertTrue(isValidCategory("pool"))
        Assertions.assertTrue(isValidCategory("POOL"))
        Assertions.assertTrue(isValidCategory("Pool"))
    }
    @Test
    fun isValidCategoryFalseWhenCategoryDoesNotExist(){
        Assertions.assertFalse(isValidCategory("G"))
        Assertions.assertFalse(isValidCategory("Poool"))
        Assertions.assertFalse(isValidCategory("Werk"))
        Assertions.assertFalse(isValidCategory(""))
    }
}