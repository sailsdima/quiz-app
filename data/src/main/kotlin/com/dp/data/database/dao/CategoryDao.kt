package com.dp.a360quiz.data.database.dao

import androidx.room.*
import com.dp.data.database.TableNames.TABLE_CATEGORY
import com.dp.data.database.model.DBCategory

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategory(category: DBCategory): Long

    @Upsert
    suspend fun upsertCategory(category: DBCategory): Long

    @Query("SELECT ${TABLE_CATEGORY}.c_id FROM $TABLE_CATEGORY WHERE c_name == :name")
    suspend fun findCategoryIdByName(name: String): Long

    @Delete
    suspend fun deleteCategory(category: DBCategory)

}