package com.zerone.maz.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zerone.maz.R
import com.zerone.maz.data.MovieData


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listArray = ArrayList<MovieData>()

    var itemClick: ((MovieData) -> Unit)? = null
    var showDialouge: ((String) -> Unit)? = null
    var count = 0



    fun setAdapterList(list: ArrayList<MovieData>) {
        this.listArray = list
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.fvrt.setImageDrawable(ContextCompat.getDrawable(holder.fvrt.context,R.drawable.ic_baseline_favorite_border_24))
        Glide.with(holder.image.context).load(listArray[position].url).into(holder.image)
        holder.textview.text = listArray[position].title

        holder.image.setOnClickListener {
            if (count != 3) {
                itemClick?.invoke(listArray[position])
                count++
            } else {
                showDialouge?.invoke("Please reach out to us")
            }
        }

        holder.adapterPosition
        if (listArray[holder.adapterPosition].isfvrt) {
            holder.fvrt.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.fvrt.context,
                    R.drawable.ic_baseline_favorite_24
                )
            )
            listArray[position].isfvrt
        } else {
            !listArray[position].isfvrt
            ContextCompat.getDrawable(
                holder.fvrt.context,
                R.drawable.ic_baseline_favorite_border_24
            )!!.constantState
        }

        holder.fvrt.setOnClickListener {
            if (holder.fvrt.drawable.constantState == ContextCompat.getDrawable(holder.fvrt.context, R.drawable.ic_baseline_favorite_border_24)!!.constantState) {
                listArray[position].isfvrt = true
                holder.fvrt.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.fvrt.context,
                        R.drawable.ic_baseline_favorite_24
                    )
                )
            } else {
                listArray[position].isfvrt = false
                holder.fvrt.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.fvrt.context,
                        R.drawable.ic_baseline_favorite_border_24
                    )
                )

            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView2)
        val textview: TextView = itemView.findViewById(R.id.textView)
        var fvrt: ImageView = itemView.findViewById(R.id.fvrt)

    }

}