package com.usati.bulletin.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.usati.bulletin.R
import com.usati.bulletin.db.ArticleDatabase
import com.usati.bulletin.repository.NewsRepository
import com.usati.bulletin.ui.fragments.ArticleNewsFragment
import com.usati.bulletin.ui.fragments.SavedNewsFragment
import com.usati.bulletin.ui.fragments.SearchNewsFragment
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        setSupportActionBar(toolbar)
        supportActionBar.apply {
            title = ""
        }

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        search_fab.setOnClickListener {
            when {
                newsNavHostFragment.childFragmentManager.fragments[0] is SavedNewsFragment -> {
                    findNavController(R.id.newsNavHostFragment)
                        .navigate(R.id.action_savedNewsFragment_to_searchNewsFragment)
                }
                newsNavHostFragment.childFragmentManager.fragments[0] is SearchNewsFragment -> {

                }
                newsNavHostFragment.childFragmentManager.fragments[0] is ArticleNewsFragment ->{

                }
                else -> {
                    findNavController(R.id.newsNavHostFragment)
                        .navigate(R.id.action_breakingNewsFragment_to_searchNewsFragment)
                }
            }
        }


    }


}