package com.hfad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.model.Tweets
import com.hfad.sliceandroidapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AllTweetsAdapter(var datas:List<Tweets>):RecyclerView.Adapter<AllTweetsAdapter.AllTweetsHolder>() {

    inner class AllTweetsHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textName=itemView.findViewById<TextView>(R.id.textName)
        val textUser=itemView.findViewById<TextView>(R.id.textUser)
        val textFav=itemView.findViewById<TextView>(R.id.textFav)
        val textId=itemView.findViewById<TextView>(R.id.textId)
        val textMsg=itemView.findViewById<TextView>(R.id.textMsg)
        val imageView=itemView.findViewById<CircleImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTweetsHolder {
        return AllTweetsHolder(LayoutInflater.from(parent.context).inflate(R.layout.alltweets_row,parent,false))
    }

    fun setTweets(datas:List<Tweets>){
        this.datas=datas
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: AllTweetsHolder, position: Int) {
        holder.apply {
            datas[position]?.let {
                textName.text=it.name
                textMsg.text=it.text
                textFav.text=it.retweetCount.toString()
                textId.text=it.favoriteCount.toString()
                textUser.text=it.handle
                Picasso.get().load(it.profileImageUrl).placeholder(R.drawable.ic_twitter_logo)
                    .into(imageView)

            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}