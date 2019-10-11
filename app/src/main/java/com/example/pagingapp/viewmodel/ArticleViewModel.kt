package com.example.pagingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingapp.data.Article
import com.example.pagingapp.network.ArticleApi
import com.example.pagingapp.paging.ArticleDataFactory
import com.example.pagingapp.paging.ArticleDataSource
import com.example.pagingapp.util.State
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ArticleViewModel(): ViewModel() {

    private val articleApi =  ArticleApi.getArticleApi()
    var articleList: LiveData<PagedList<Article>>
    private val pageSize = 20
    private var articleDataFactory: ArticleDataFactory
    private val executor: Executor

    init {
        executor = Executors.newSingleThreadExecutor()
        articleDataFactory = ArticleDataFactory(articleApi)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        articleList = LivePagedListBuilder<Int, Article>(articleDataFactory, config)
            .setFetchExecutor(executor)
            .build()

    }

    fun getState():LiveData<State> = Transformations.switchMap<ArticleDataSource, State>(articleDataFactory.articleDataSource, ArticleDataSource::state)

    fun getArticleLiveData(): LiveData<PagedList<Article>> = articleList

    fun emptyList(): Boolean{
        return articleList.value?.isEmpty() ?: true
    }

}