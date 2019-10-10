package com.example.pagingapp.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.pagingapp.data.Article
import com.example.pagingapp.data.ArticleList
import com.example.pagingapp.network.ArticleApi
import com.example.pagingapp.util.KEY
import com.example.pagingapp.util.QUERY
import com.example.pagingapp.util.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleDataSource(val articleApi: ArticleApi) : PageKeyedDataSource<Int, Article>() {

    private val TAG = ArticleDataSource::class.java.simpleName
    var state:MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        updateState(State.LOADING)
        val request = articleApi.getArticles(
            QUERY,
            KEY, 1, params.requestedLoadSize)
        request.enqueue(object : Callback<ArticleList>{

            override fun onFailure(call: Call<ArticleList>, throwable: Throwable) {
                Log.d(TAG, "onFailure message: ${throwable.message}")
                updateState(State.ERROR)
            }

            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {

                if (!response.isSuccessful){
                    updateState(State.ERROR)
                }

                if (response.isSuccessful){
                    if (response.body() != null) {
                        callback.onResult(response.body()!!.articles, null, 2)
                        updateState(State.DONE)
                    }
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

        updateState(State.LOADING)
        val request = articleApi.getArticles(
            QUERY,
            KEY, params.key, params.requestedLoadSize)
        request.enqueue(object :Callback<ArticleList>{

            override fun onFailure(call: Call<ArticleList>, throwable: Throwable) {
                Log.d(TAG, "onFailure message: ${throwable.message}")
                updateState(State.ERROR)
            }

            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {
                if (!response.isSuccessful){
                    updateState(State.ERROR)
                }

                if (response.isSuccessful){
                    if (response.body() != null) {
                        callback.onResult(response.body()!!.articles, params.key + 1)
                        updateState(State.DONE)
                    }
                }
            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {}

    private fun updateState(state: State){
        this.state.postValue(state)
    }
}