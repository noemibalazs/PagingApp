package com.example.pagingapp.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ArticleList(
    @field:SerializedName("articles") val articles: MutableList<Article>
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createTypedArrayList(Article.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(articles)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ArticleList> = object : Parcelable.Creator<ArticleList> {
            override fun createFromParcel(source: Parcel): ArticleList =
                ArticleList(source)
            override fun newArray(size: Int): Array<ArticleList?> = arrayOfNulls(size)
        }
    }
}