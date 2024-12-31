package com.example.getapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.getapi.R
import com.example.getapi.ResponseModel

class RecyclerAdapter(var context: Context, var list:ArrayList<ResponseModel.Data>, var recyclerInterface: RecyclerInterface ):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        val Fname=view.findViewById<TextView>(R.id.tvFirstName)
        val Lname=view.findViewById<TextView>(R.id.tvSecondName)
        val email=view.findViewById<TextView>(R.id.tvemail)
        val delete=view.findViewById<TextView>(R.id.deleteBtn)
//        val update=view.findViewById<TextView>(R.id.updateBtn)
        val item=view.findViewById<ConstraintLayout>(R.id.constraintAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Fname.text=list[position].first_name
        holder.Lname.text=list[position].last_name
        holder.email.text=list[position].email
        holder.delete.setOnClickListener { recyclerInterface.delete(position) }
        holder.item.setOnClickListener { recyclerInterface.itemClick(position,list[position]) }

    }
}