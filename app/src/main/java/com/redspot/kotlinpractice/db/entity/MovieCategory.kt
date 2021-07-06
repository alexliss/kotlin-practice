package com.redspot.kotlinpractice.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class MovieCategory(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long,
    val name: String
    )
