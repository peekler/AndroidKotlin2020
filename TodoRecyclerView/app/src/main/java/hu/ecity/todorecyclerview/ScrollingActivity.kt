package hu.ecity.todorecyclerview

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import hu.ecity.todorecyclerview.adapter.TodoAdapter
import hu.ecity.todorecyclerview.data.Todo
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            TodoDialog().show(supportFragmentManager,
                "TODO_DIALOG")
        }

        todoAdapter = TodoAdapter(this)

        recyclerTodo.layoutManager = GridLayoutManager(this,2)

        recyclerTodo.adapter = todoAdapter
    }

    override fun todoCreated(todo: Todo) {
        todoAdapter.addTodo(todo)
    }

}
