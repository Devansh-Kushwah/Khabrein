package com.example.khabrein

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listner: NewsitemClicked) : RecyclerView.Adapter<NewsViewHolder>(){
    private val items: ArrayList<newsdata> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentitem = items[position]
        holder.titleView.text = currentitem.title
        holder.author.text = "-"+currentitem.author
        if (holder.author.text=="null"){
            holder.author.text = "unknown"
        }
        Glide.with(holder.itemView.context).load(currentitem.urlToImage).into(holder.image)
    }




    fun updatenews(updateditems: ArrayList<newsdata>){
        items.clear()
        items.addAll(updateditems)

        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return items.size
    }

}


class NewsViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}



interface NewsitemClicked{
    fun onItemClicked(item: newsdata)
}





