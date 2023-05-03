package com.example.quotesapp

import android.app.Application
import com.example.quotesapp.api.QuoteService
import com.example.quotesapp.api.RetrofitHelper
import com.example.quotesapp.db.QuoteDataBase
import com.example.quotesapp.repository.QuotesRepository

class QuotesApplication: Application() {
    lateinit var quotesRepository: QuotesRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService= RetrofitHelper.getInstance().create(QuoteService::class.java)
        val dataBase=QuoteDataBase.getDataBase(applicationContext)
        quotesRepository= QuotesRepository(quoteService,dataBase,applicationContext)
    }
}