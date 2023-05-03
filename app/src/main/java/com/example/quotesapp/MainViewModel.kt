package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.db.QuoteDataBase
import com.example.quotesapp.models.QuoteList
import com.example.quotesapp.repository.QuotesRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.nio.charset.Charset

class MainViewModel(private val repository: QuotesRepository):ViewModel() {
    private var quoteList:Array<com.example.quotesapp.models.Result> = emptyArray()
    private var index = 0

    init {
        viewModelScope.launch {
            repository.getQuotes(1)
        }
//        quoteList=getQuotes()
    }

    val quotes:LiveData<QuoteList>
    get() = repository.quotes


//    fun getQuote() =quoteList[index]

//    fun nextQuote()=quoteList[++index % quoteList.size]

//    fun preQuote()=quoteList[(--index + quoteList.size) % quoteList.size]
}