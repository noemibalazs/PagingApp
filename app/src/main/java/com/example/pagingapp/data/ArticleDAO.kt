package com.example.pagingapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pagingapp.util.ARTICLE_DB

@Dao
interface ArticleDAO {

    @Query("SELECT * FROM articles")
    fun getArticleList(): LiveData<MutableList<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArticle2DB(article: Article)

    @Query("SELECT * FROM articles WHERE author =:author")
    fun getArticle(author: String): LiveData<Article>

    companion object{

        fun getArticleDAO(context: Context): ArticleDAO {
            return Room.databaseBuilder(context, ArticleDataBase::class.java,
                ARTICLE_DB
            ).build().getDao()
        }
    }
}