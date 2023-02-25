package com.dp.a360quiz.data.database.dao

import androidx.room.*
import com.dp.a360quiz.data.database.TableNames.TABLE_CATEGORY
import com.dp.a360quiz.data.database.model.DBCategory

@Dao
interface CategoryDao {

    @Upsert
    fun upsertCategory(category: DBCategory): Long

    @Query("SELECT ${TABLE_CATEGORY}.c_id FROM $TABLE_CATEGORY WHERE c_name == :name")
    fun findCategoryIdByName(name: String): Long

    @Delete
    fun deleteCategory(category: DBCategory)

}