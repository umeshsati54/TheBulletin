package com.usati.bulletin.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.usati.bulletin.R
import com.usati.bulletin.ui.NewsActivity
import com.usati.bulletin.ui.NewsViewModel

class ArticleNewsFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}