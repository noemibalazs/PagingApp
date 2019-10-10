package com.example.pagingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pagingapp.R
import com.example.pagingapp.adapter.ArticleAdapter
import com.example.pagingapp.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        adapter = ArticleAdapter(this)

        initRecycleView()
        setState()
    }

    private fun initRecycleView(){
        myRecycler.setHasFixedSize(true)
        viewModel.getArticleLiveData().observe(this, Observer {
            adapter.submitList(it)
            myRecycler.adapter = adapter
        })
    }

    private fun setState(){
        viewModel.getState().observe(this, Observer {
            adapter.setState(it)
        })
    }
}
