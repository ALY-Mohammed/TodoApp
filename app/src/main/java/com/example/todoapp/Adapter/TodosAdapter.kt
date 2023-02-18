package com.example.todoapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapp.R
import com.example.todoapp.TodoDatabase.MyDatabase
import com.example.todoapp.TodoDatabase.Todo
import com.zerobranch.layout.SwipeLayout
import java.util.Calendar

class TodosAdapter(var items :List<Todo>):Adapter<TodosAdapter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.todo_element_of_recyclerview,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val todo = items[position]
          //  holder.deleat.setImageResource(R.drawable.deleat)

             holder.titletextView.text = todo.title
             holder.titletextView.setTextColor(Color.rgb(93,155,235))
             holder.descriptiontextView.text = todo.description
             holder.descriptiontextView.setTextColor(Color.rgb(93,155,235))
             holder.iconCheck.setImageResource(R.drawable.check_icon)

        if (todo.isDone == true){
            holder.iconCheck.setImageResource(R.drawable.done)
            holder.titletextView.setTextColor(Color.GREEN)
            holder.descriptiontextView.setTextColor(Color.GREEN)
        }

        holder.itemviewLayout.setOnClickListener{
            onItemClick?.onItemClicked(todo)
        }
        holder.iconCheck.setOnClickListener {
            oncorecktlick?.onChecked(holder,todo)
        }

        holder.deleat.setOnClickListener {
            onItemDeletedClicked?.onItemClick(position,items!!.get(position))
            holder.swipe.close()
        }


    }

    class viewHolder(itemView:View):ViewHolder(itemView){
        val titletextView= itemView.findViewById<TextView>(R.id.itemTitleText)
        val descriptiontextView= itemView.findViewById<TextView>(R.id.itemDescreptionText)
        val iconCheck =itemView.findViewById<ImageView>(R.id.ImageCorrect)
        val deleat=itemView.findViewById<ImageView>(R.id.itemDelete)
        val swipe =itemView.findViewById<SwipeLayout>(R.id.swipe)
        val itemviewLayout=itemView.findViewById<ConstraintLayout>(R.id.itemView)

    }

    fun changeList(newtodo:List<Todo>){

        items=newtodo
        notifyDataSetChanged()
    }


    var onItemClick:OnItemClick?=null
    interface OnItemClick{
        fun onItemClicked(todo: Todo)
    }


    var oncorecktlick:Oncorecktlick?=null
    interface Oncorecktlick{
        fun onChecked(v:viewHolder,todo: Todo)
    }

    var onItemDeletedClicked:OnItemDeletedClicked?=null
    interface OnItemDeletedClicked{
        fun onItemClick(position: Int, todo: Todo)
    }
}