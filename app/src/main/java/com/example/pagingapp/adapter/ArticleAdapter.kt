package com.example.pagingapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingapp.R
import com.example.pagingapp.biding.ArticleListener
import com.example.pagingapp.data.Article
import com.example.pagingapp.databinding.ItemArticleBinding
import com.example.pagingapp.util.ARTICLE_VIEW_TYPE
import com.example.pagingapp.util.GradientColor
import com.example.pagingapp.util.PROGRESS_VIEW_TYPE
import com.example.pagingapp.util.State

class ArticleAdapter( val context: Context) : PagedListAdapter<Article, RecyclerView.ViewHolder>(
    DIF_UTIL
), ArticleListener {

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val articleBinding = DataBindingUtil.inflate<ItemArticleBinding>(LayoutInflater.from(context), R.layout.item_article, parent,false)
        val view = LayoutInflater.from(context).inflate(R.layout.item_network_state, parent, false)
        return when(viewType){
            ARTICLE_VIEW_TYPE -> { ArticleVH(articleBinding) }
            else -> {ProgressVH(view)}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == ARTICLE_VIEW_TYPE){
            (holder as ArticleVH).bindArticle(getItem(position)!!)
        }else{
            (holder as ProgressVH).bind(state)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) ARTICLE_VIEW_TYPE else PROGRESS_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasProgress()) 1 else 0
    }

    private fun hasProgress(): Boolean{
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State){
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    override fun onArticleClicked(article: Article) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.apply {
            setData(Uri.parse(article.url))
        }
        if (intent.resolveActivity(context.packageManager) != null){
            context.startActivity(intent)
        }
    }

    companion object{

        val DIF_UTIL = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.author == newItem.author
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.author == newItem.author &&
                        oldItem.title == newItem.title &&
                        oldItem.description == newItem.description &&
                        oldItem.publishedAt == newItem.publishedAt
            }
        }
    }

    inner class ArticleVH(val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root){

        fun bindArticle(article: Article){
            binding.article = article
            binding.executePendingBindings()
            binding.listener = this@ArticleAdapter
        }
    }

    inner class ProgressVH(view: View): RecyclerView.ViewHolder(view){
        val progressBar = view.findViewById<ProgressBar>(R.id.stateProgressBar)
        val text = view.findViewById<GradientColor>(R.id.networkState)

        fun bind(state: State){
            progressBar.visibility = if (state == State.LOADING) View.VISIBLE else View.GONE
            text.visibility = if (state == State.LOADING) View.VISIBLE else View.GONE
        }
    }


}