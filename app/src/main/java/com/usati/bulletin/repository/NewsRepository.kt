package com.usati.bulletin.repository

import com.usati.bulletin.api.RetrofitInstance
import com.usati.bulletin.db.ArticleDatabase
import com.usati.bulletin.models.Article
import retrofit2.http.Query

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getTopNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun delete(article: Article) = db.getArticleDao().delete(article)

    suspend fun exists(title: String) : Boolean = db.getArticleDao().exists(title)
}