package com.example.pagingapp.biding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pagingapp.R

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
    }
}