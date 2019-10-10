package com.example.pagingapp.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey
    @field:SerializedName("author") val author: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("urlToImage") val urlToImage: String,
    @field:SerializedName("publishedAt") val publishedAt: String,
    @field:SerializedName("content") val content: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(author)
        writeString(title)
        writeString(description)
        writeString(url)
        writeString(urlToImage)
        writeString(publishedAt)
        writeString(content)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article> {
            override fun createFromParcel(source: Parcel): Article =
                Article(source)
            override fun newArray(size: Int): Array<Article?> = arrayOfNulls(size)
        }
    }

}