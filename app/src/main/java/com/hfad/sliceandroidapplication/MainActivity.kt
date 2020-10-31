package com.hfad.sliceandroidapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.adapter.AllTweetsAdapter
import com.hfad.model.Tweets
import com.hfad.viewmodel.TweetsViewModel
import com.hfad.viewmodel.TweetsViewModel_Factory
import com.hfad.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var tweetsviewmodelFactory: ViewModelFactory

    @Inject
    lateinit var tweetsviewModel: TweetsViewModel

    lateinit var btnSearch:Button
    lateinit var rvAllTweets:RecyclerView
    lateinit var nodatasCard:CardView
    lateinit var datasCard:CardView
    lateinit var etSearch:EditText
    var datas:List<Tweets> = arrayListOf()
    lateinit var adapterTweet: AllTweetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        rvAllTweets=findViewById(R.id.rvAllTweets)
        datasCard=findViewById(R.id.datasCard)
        nodatasCard=findViewById(R.id.nodatasCard)
        btnSearch=findViewById(R.id.btnSearch)
        etSearch=findViewById(R.id.etSearch)
        tweetsviewModel = tweetsviewmodelFactory.create(tweetsviewModel::class.java)


            tweetsviewModel.getTweets()

        btnSearch.setOnClickListener {
            if (etSearch.text.toString().isNullOrEmpty() || etSearch.text.toString().length<3){
                adapterTweet.setTweets(datas)
            }else{
                val tweets =datas.filter { it.name.toLowerCase().equals(etSearch.text.toString().toLowerCase()) ||  it.text.toLowerCase().equals(etSearch.text.toString().toLowerCase()) || it.handle.toLowerCase().equals(etSearch.text.toString().toLowerCase())  }

                if (tweets.size>0){
                    datasCard.visibility=VISIBLE
                    nodatasCard.visibility= GONE
                    adapterTweet.setTweets(tweets)
                }else{
                    nodatasCard.visibility= VISIBLE
                    datasCard.visibility=GONE
                }

            }
        }

        etSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (etSearch.text.toString().length<3){
                    adapterTweet.setTweets(datas)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        tweetsviewModel.liveData.observe(this, Observer {
            if (it.data.size>0){
                datasCard.visibility=VISIBLE
                nodatasCard.visibility= GONE
            }else{
                nodatasCard.visibility= VISIBLE
                datasCard.visibility=GONE
            }
            datas=it.data
            adapterTweet=AllTweetsAdapter(datas = it.data)
            rvAllTweets.apply {
                layoutManager=LinearLayoutManager(this@MainActivity)
                adapter=adapterTweet
                setHasFixedSize(true)
            }
        })

        tweetsviewModel.errorData.observe(this,  {
            datasCard.visibility=GONE

            nodatasCard.visibility=VISIBLE
        })
    }
}