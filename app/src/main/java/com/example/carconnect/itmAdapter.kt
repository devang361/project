package com.example.carconnect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class itmAdapter(private val itmlst:ArrayList<itemDs>):RecyclerView.Adapter<itmAdapter.itmHolder>() {
    class itmHolder(itmView:View):RecyclerView.ViewHolder(itmView){
        val itmtitle:TextView = itemView.findViewById(R.id.r_title)
        val itmbrand:TextView = itemView.findViewById(R.id.r_brand)
        val itmmodel:TextView = itemView.findViewById(R.id.r_model)
        val itmyear:TextView = itemView.findViewById(R.id.r_year)
        val itmcondition:TextView = itemView.findViewById(R.id.r_condition)
        val itmprice:TextView = itemView.findViewById(R.id.r_price)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itmHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return itmHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itmlst.size
    }

    override fun onBindViewHolder(holder: itmHolder, position: Int) {
        val currentitem = itmlst[position]
        holder.itmtitle.setText(currentitem.title.toString())
        holder.itmmodel.setText(currentitem.model.toString())
        holder.itmbrand.setText(currentitem.brand.toString())
        holder.itmyear.setText(currentitem.year.toString())
        holder.itmcondition.setText(currentitem.condition.toString())
        holder.itmprice.setText(currentitem.price.toString())


    }
}