package com.example.apimars.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apimars.R
import com.example.apimars.network.MarsState

class MarsStateAdapter(private val listStates : List<MarsState>) : RecyclerView.Adapter<MarsStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsStateAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mars_state_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MarsStateAdapter.ViewHolder, position: Int) {
        holder.id.text = listStates[position].id
        holder.price.text = "$" + listStates[position].price.toString()
        holder.type.text = listStates[position].type.capitalize()

        Glide.with(holder.itemView.context).load(listStates[position].img_src).error(R.drawable.ic_launcher_foreground).into(holder.image)
        //use cleatTextTraffic atributte in Manifest to correct function
    }

    override fun getItemCount(): Int = listStates.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id = itemView.findViewById<TextView>(R.id.idMarsState)
        val image = itemView.findViewById<ImageView>(R.id.imageMarsState)
        val type = itemView.findViewById<TextView>(R.id.typeMarsState)
        val price = itemView.findViewById<TextView>(R.id.priceMarsState)

        init { setListeners() }

        private fun setListeners() {
            itemView.setOnClickListener { Toast.makeText(itemView.context, "State Id: ${listStates[adapterPosition].id}", Toast.LENGTH_SHORT).show() }
        }
    }

}