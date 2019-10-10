package com.example.pagingapp.biding

import com.example.pagingapp.data.Article

interface ArticleListener {
    fun onArticleClicked(article: Article)
}