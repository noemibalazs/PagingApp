package com.example.pagingapp.paging

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagingapp.data.Article
import com.example.pagingapp.network.ArticleApi

class ArticleDataFactory(val articleApi: ArticleApi) : DataSource.Factory<Int, Article>() {

    var articleDataSource: MutableLiveData<ArticleDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Article> {
        val articleDS = ArticleDataSource(articleApi)
        articleDataSource.postValue(articleDS)
        return articleDS
    }
}