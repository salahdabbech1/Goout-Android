package com.salah.goout.lists.task

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salah.goout.R

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title : TextView = itemView.findViewById(R.id.taskName)
    val desc : TextView = itemView.findViewById(R.id.taskDescription)

}
