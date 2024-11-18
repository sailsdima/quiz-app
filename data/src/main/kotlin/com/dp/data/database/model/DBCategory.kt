package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_CATEGORY

@Entity(
    tableName = TABLE_CATEGORY,
    indices = [Index("c_id", unique = true), Index("c_name", unique = true)]
)
data class DBCategory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "c_id")
    val id: Long,
    @ColumnInfo(name = "c_name")
    val name: String,
)