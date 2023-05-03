package com.example.quotesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotesapp.models.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDataBase :RoomDatabase(){
    abstract fun quoteDao(): QuotesDao
    companion object{
        @Volatile
        private var INSTANCE:QuoteDataBase?=null

        fun getDataBase(context: Context): QuoteDataBase{
            if(INSTANCE==null)
            {
                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context,
                    QuoteDataBase::class.java,
                    "QuoteDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}