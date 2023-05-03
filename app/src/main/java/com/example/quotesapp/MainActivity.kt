package com.example.quotesapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.api.QuoteService
import com.example.quotesapp.api.RetrofitHelper
import com.example.quotesapp.databinding.ActivityMainBinding
import com.example.quotesapp.repository.QuotesRepository

class MainActivity : AppCompatActivity() {

    private lateinit var quoteList:List<com.example.quotesapp.models.Result>
    private var index:Int=0;
    var size:Int = 0

    //View Model
    private lateinit var mainViewModel: MainViewModel

    //Binding
    private lateinit var binding:ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //binding
        binding=ActivityMainBinding.inflate(layoutInflater)
        //for no title bar
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)


        //for notch cutout
//        window.attributes.layoutInDisplayCutoutMode=WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//        WindowInsetsControllerCompat(window,binding.root).let { controller->
//            controller.hide(WindowInsetsCompat.Type.systemBars())
//            controller.systemBarsBehavior=WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
//        }

        val repository=(application as QuotesApplication).quotesRepository

        mainViewModel= ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this) {
            quoteList=it.results
            size=quoteList.size
            setQuote(it.results[0])
        }
//        setQuote(mainViewModel.getQuote())

    }

    private fun setQuote(quote: com.example.quotesapp.models.Result){
        binding.quoteText.text=quote.content
        binding.quoteAuthor.text=quote.author
    }

    fun onPrevious(view: View) {
//        setQuote(mainViewModel.preQuote())
//        mainViewModel.quotes.observe(this){
//            setQuote(it.results[--index])
//        }
        setQuote(quoteList[(--index + (quoteList.size-2)) % (quoteList.size-2)])
    }

    fun onNext(view: View) {
//        setQuote(mainViewModel.nextQuote())
//        mainViewModel.quotes.observe(this){
//            setQuote(it.results[++index])
//        }
        index=(index+1)%(size-1)
        setQuote(quoteList[++index % (quoteList.size-2)])
    }

    fun onShare(view: View) {
//        val intent=Intent(Intent.ACTION_SEND)
//        intent.type = "text/plain"
//        intent.putExtra(Intent.EXTRA_TEXT,quoteList[index])
//        startActivity(intent)
    }
}