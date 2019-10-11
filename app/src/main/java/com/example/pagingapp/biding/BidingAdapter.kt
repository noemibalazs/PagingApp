package com.example.pagingapp.biding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pagingapp.R
import com.example.pagingapp.data.Article
import com.example.pagingapp.util.DateUtils

class BidingAdapter {

    companion object{

        @BindingAdapter("android:src")
        @JvmStatic
        fun setImage(view:ImageView, url:String){
            Glide.with(view.context)
                .load(url)
                .error(R.drawable.news)
                .placeholder(R.drawable.news)
                .into(view)
        }

        @BindingAdapter("android:txt")
        @JvmStatic
        fun setText(view:TextView, article:Article){
            if (article.author.isNullOrEmpty()){
                view.text = DateUtils().getDate(article.publishedAt)
            }else{
                view.text = article.author
            }
        }
    }
}