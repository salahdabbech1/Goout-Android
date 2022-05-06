package com.example.goout.lists.mykidslist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.goout.R
import com.example.goout.model.Kid
import com.example.goout.ui.MainActivity

class MyKidsAdapter(var mykidslist : MutableList<Kid>):RecyclerView.Adapter<MyKidsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyKidsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mykids_single_item,parent,false)

    return MyKidsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyKidsViewHolder, position: Int) {
        val name = mykidslist[position].Name
        val email = mykidslist[position].Email
        println(name+email)
        holder.Name.text = name
        holder.Email.text = email

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
       return mykidslist.size
    }

}