package com.example.pagingapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Article::class], exportSchema = false, version = 1)
abstract class ArticleDataBase: RoomDatabase() {

    abstract fun getDao(): ArticleDAO
}