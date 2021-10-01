package com.usati.bulletin.repository

import com.usati.bulletin.api.RetrofitInstance
import com.usati.bulletin.db.ArticleDatabase
import retrofit2.http.Query

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getTopNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery,pageNumber)
}