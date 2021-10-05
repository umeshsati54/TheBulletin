package com.usati.bulletin.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.usati.bulletin.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT EXISTS (SELECT title FROM articles WHERE title = :title)")
    suspend fun exists(title: String): Boolean
}