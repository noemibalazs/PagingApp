package com.example.pagingapp.network

import com.example.pagingapp.data.ArticleList
import com.example.pagingapp.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("everything")
    fun getArticles( @Query("q") query: String,
                     @Query("apiKey") apiKey: String,
                     @Query("page") page:Int,
                     @Query("pageSize") pageSize:Int): Call<ArticleList>


    companion object{

        fun getArticleApi(): ArticleApi {

            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(ArticleApi::class.java)
        }
    }
}