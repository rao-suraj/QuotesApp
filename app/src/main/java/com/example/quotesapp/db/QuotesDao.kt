package com.example.quotesapp.db

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuotesDao {
    @Insert
    suspend fun addQuotes(quotes:List<com.example.quotesapp.models.Result>)
    @Query("Select * from quote")
    suspend fun getQuotes() : List<com.example.quotesapp.models.Result>
}