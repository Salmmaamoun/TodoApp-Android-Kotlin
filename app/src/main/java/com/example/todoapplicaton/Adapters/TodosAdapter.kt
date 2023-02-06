package com.example.todoapplicaton.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplicaton.R
import com.example.todoapplicaton.database.model.Task
import com.example.todoapplicaton.databinding.ItemTodoBinding
import java.util.zip.Inflater

class TodosAdapter(var items:List<Task>): RecyclerView.Adapter<TodosAdapter.ViewHolder>() {
    lateinit var binding:ItemTodoBinding
    var onItemClicked: OnItemClicked? =null
    var onItemDeleteClickd:OnItemDeleteClickd?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val task=items.get(position)
        holder.bind(task)
        holder.binding.consroot.setOnClickListener{
            onItemClicked?.onItemClick(task)
        }
        if(items[position].isDone) {
            holder.binding.done.setBackgroundColor(Color.GREEN)
            holder.binding.titleTextView.setBackgroundColor(Color.GREEN)
            holder.binding.done.setImageResource(R.drawable.makedone)
            holder.binding.done.setImageResource(R.drawable.icon_check)
        }
       holder.binding.layoutswipe.setOnClickListener {
            onItemDeleteClickd?.onItemDeleteClick(position,task)
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
    fun changData(newItems:List<Task>){
        items=newItems
        notifyDataSetChanged()

    }
    class ViewHolder(var binding:ItemTodoBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(task:Task){
           binding.titleTextView.text=task.title
           binding.descriptionTextView.text=task.description
        }



    }

}
interface OnItemClicked{
    fun onItemClick(task:Task)

}
interface OnItemDeleteClickd{
    fun onItemDeleteClick(pos:Int,task:Task)
}