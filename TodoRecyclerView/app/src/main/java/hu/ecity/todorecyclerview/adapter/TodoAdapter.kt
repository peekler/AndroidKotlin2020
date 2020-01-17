package hu.ecity.todorecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ecity.todorecyclerview.R
import hu.ecity.todorecyclerview.data.Todo
import kotlinx.android.synthetic.main.todo_row.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    var todoItems = mutableListOf<Todo>()

    val context : Context

    constructor(context: Context) :super() {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoView = LayoutInflater.from(context).inflate(
            R.layout.todo_row, parent, false
        )
        return ViewHolder(todoView)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoItems[position]

        holder.tvDate.text=todo.createDate
        holder.cbDone.text=todo.todoText
        holder.cbDone.isChecked=todo.done

        holder.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }
    }

    fun deleteTodo(position: Int) {
        todoItems.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addTodo(todo: Todo) {
        todoItems.add(todo)
        //notifyDataSetChanged()
        notifyItemInserted(todoItems.lastIndex)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate = itemView.tvDate
        val cbDone = itemView.cbDone
        val btnDelete = itemView.btnDelete
    }
}