package hu.ecity.todorecyclerview.adapter

import android.animation.Animator
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import hu.ecity.todorecyclerview.R
import hu.ecity.todorecyclerview.ScrollingActivity
import hu.ecity.todorecyclerview.data.AppDatabase
import hu.ecity.todorecyclerview.data.Todo
import hu.ecity.todorecyclerview.touch.TodoTouchHelperCallback
import kotlinx.android.synthetic.main.todo_row.view.*
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>, TodoTouchHelperCallback {

    var todoItems = mutableListOf<Todo>()

    val context : Context

    constructor(context: Context, todoList: List<Todo>) :super() {
        this.context = context

        todoItems.addAll(todoList)
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

        holder.itemView.tvTodo.text = todo.todoText

        holder.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }

        holder.btnEdit.setOnClickListener {
            (context as ScrollingActivity).showEditTodoDialog(todo,
                holder.adapterPosition)
        }

        holder.cbDone.setOnClickListener {
            todo.done = holder.cbDone.isChecked
            updateTodo(todo)
        }

        when (todo.category) {
            Todo.CATEGORY_WORK -> {
                holder.itemView.todoImage.setImageResource(R.drawable.work)
                setMatchingTextColor(R.drawable.work, holder)
            }
            Todo.CATEGORY_FAMILY -> {
                holder.itemView.todoImage.setImageResource(R.drawable.family)
                setMatchingTextColor(R.drawable.family, holder)
            }
            Todo.CATEGORY_OTHERS -> {
                holder.itemView.todoImage.setImageResource(R.drawable.others)
                setMatchingTextColor(R.drawable.others, holder)
            }
        }

        holder.itemView.tvTodo.setOnClickListener {
            if (holder.itemView.editLayout.visibility == View.INVISIBLE) {
                revealEditLayout(holder.itemView.editLayout)
            } else {
                hideEditLayout(holder.itemView.editLayout)
            }
        }
    }

    fun revealEditLayout(editLayout: LinearLayout) {
        val x = editLayout.getRight()
        val y = editLayout.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(editLayout.getWidth().toDouble(),
            editLayout.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            editLayout,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        editLayout.visibility = View.VISIBLE
        anim.start()
    }

    fun hideEditLayout(editLayout: LinearLayout) {
        val x = editLayout.getRight()
        val y = editLayout.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(editLayout.getWidth().toDouble(),
            editLayout.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            editLayout,
            x,
            y,
            endRadius.toFloat(),
            startRadius.toFloat()
        )


        anim.start()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                editLayout.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })

    }

    fun setMatchingTextColor(imageRes: Int, holder: ViewHolder) {
        val photo = BitmapFactory.decodeResource(context.resources,
            imageRes)
        Palette.from(photo).generate { palette ->
            val bgColor = palette!!.getMutedColor(
                ContextCompat.getColor(context,
                    android.R.color.black))
            holder.itemView.placeNameHolder.setBackgroundColor(bgColor!!)

            val editBgColor = palette!!.getLightMutedColor(
                ContextCompat.getColor(context,
                    android.R.color.black))
            holder.itemView.editLayout.setBackgroundColor(editBgColor!!)
        }
    }


    fun updateTodo(todo: Todo) {
        Thread {
            AppDatabase.getInstance(context).todoDao().updateTodo(todo)
        }.start()
    }

    fun deleteTodo(position: Int) {
        Thread {
            AppDatabase.getInstance(context).todoDao().deleteTodo(todoItems[position])

            (context as ScrollingActivity).runOnUiThread {
                todoItems.removeAt(position)
                notifyItemRemoved(position)
            }
        }.start()
    }

    fun addTodo(todo: Todo) {
        todoItems.add(todo)
        //notifyDataSetChanged()
        notifyItemInserted(todoItems.lastIndex)
    }

    fun updateTodoOnPosition(todo: Todo, index: Int) {
        todoItems.set(index, todo)
        notifyItemChanged(index)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onDismissed(position: Int) {
        deleteTodo(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate = itemView.tvDate
        val cbDone = itemView.cbDone
        val btnDelete = itemView.btnDelete

        val btnEdit = itemView.btnEdit
    }
}