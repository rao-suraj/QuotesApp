package com.example.quotesapp.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotesapp.MainActivity
import com.example.quotesapp.api.QuoteService
import com.example.quotesapp.db.QuoteDataBase
import com.example.quotesapp.models.QuoteList
import com.example.quotesapp.utils.NetworkUtils

class QuotesRepository(private val quotesService: QuoteService,private val quoteDataBase: QuoteDataBase,private val context: Context) {

    private val quotesLiveData =  MutableLiveData<QuoteList>()

    val quotes:LiveData<QuoteList>
    get() = quotesLiveData


    suspend fun getQuotes(page:Int){

        if(NetworkUtils.checkConnection(context))
        {
            Log.d("INTERNET","working")
            val result =    quotesService.getQuotes(page)
            if(result?.body() != null)
            {
                quoteDataBase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }else
        {
            Log.d("INTERNET","Not working")
            val quotes =quoteDataBase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)
        }



    }
}