package com.usati.bulletin.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.usati.bulletin.R
import com.usati.bulletin.ui.NewsActivity
import com.usati.bulletin.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class ArticleNewsFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    private val args: ArticleNewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        val article = args.article

        CoroutineScope(Dispatchers.IO).launch{
            val exists = viewModel.isNewsSaved(article.title!!)
            if(exists){
                withContext(Dispatchers.Main) {
                    fab.setImageResource(R.drawable.ic_favorite)
                }
            }
            else {
                withContext(Dispatchers.Main) {
                    fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }



        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url!!)
        }

        fab.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val exists = viewModel.isNewsSaved(article.title!!)

                withContext(Dispatchers.Main){
                if (!exists) {

                    fab.setImageResource(R.drawable.ic_favorite)
                    viewModel.saveArticle(article)
                    Snackbar.make(
                        view,
                        "Article Saved",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()

                } else {
                    fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    viewModel.deleteArticle(article)
                    Snackbar.make(
                        view,
                        "Article Removed",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }
            }
            }

        }
    }

}