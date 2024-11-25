package com.dp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.dp.data.database.TableNames.TABLE_CATEGORY
import com.dp.data.database.model.DBCategory

@Dao
interface CategoryDao {

    /**
     * Inserts a new category into the database.
     *
     * @param category The category to be inserted.
     * @return The ID of the newly inserted category.
     */
    @Insert
    suspend fun insertCategory(category: DBCategory): Long

    /**
     * Inserts or updates the given category in the database.
     * If the category already exists, it will be updated.
     *
     * @param category The category to be inserted or updated.
     * @return The ID of the inserted or updated category.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertCategory(category: DBCategory): Long

    /**
     * Retrieves the ID of a category by its name.
     *
     * @param name The name of the category to search for.
     * @return The ID of the category with the given name.
     */
    @Query("SELECT ${TABLE_CATEGORY}.c_id FROM $TABLE_CATEGORY WHERE c_name == :name")
    suspend fun findCategoryIdByName(name: String): Long

    /**
     * Deletes a category from the database.
     *
     * @param category The category to be deleted.
     */
    @Delete
    suspend fun deleteCategory(category: DBCategory)
}
