package com.implude.dreamplude.view.function

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.implude.dreamplude.R
import com.implude.dreamplude.view.bluetooth.models.FunctionItem


class FunctionRecyclerViewAdapter(private val context: Context, val items: ArrayList<FunctionItem>) : RecyclerView.Adapter<FunctionRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.function_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]

        holder.title.text = item.title
        holder.image.setImageResource(item.image)
        holder.root.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView = itemView.findViewById(R.id.title)
        internal var root: CardView = itemView.findViewById(R.id.layout_root)
        internal var image: ImageView = itemView.findViewById(R.id.image)
    }
}