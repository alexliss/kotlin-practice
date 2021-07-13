package com.redspot.kotlinpractice.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "categories", indices = [Index("categoryName", unique = true)])
data class MovieCategory(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0,
    @ColumnInfo(name = "categoryName")
    val name: String
)
