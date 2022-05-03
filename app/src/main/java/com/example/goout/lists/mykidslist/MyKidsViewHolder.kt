package com.example.goout.lists.mykidslist

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goout.R

class MyKidsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
val Childimage  : ImageView = itemView.findViewById(R.id.childimage)
val Name  : TextView = itemView.findViewById(R.id.Name)
val Email : TextView = itemView.findViewById(R.id.email)
val progress : ProgressBar = itemView.findViewById(R.id.progress)

}