package hu.ecity.todorecyclerview

import android.os.Bundle
import android.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import hu.ecity.todorecyclerview.adapter.TodoAdapter
import hu.ecity.todorecyclerview.data.AppDatabase
import hu.ecity.todorecyclerview.data.Todo
import hu.ecity.todorecyclerview.touch.TodoReyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt

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

        initRecyclerView()

        if (!wasStartedBefore()) {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.fab)
                .setPrimaryText("New todo")
                .setSecondaryText("Click here to add new todo")
                .show()
            saveWasStarted()
        }
    }
    

    fun saveWasStarted() {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = sharedPref.edit()
        editor.putBoolean("KEY_STARTED", true)
        editor.apply()
    }

    fun wasStartedBefore() : Boolean {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        return sharedPref.getBoolean("KEY_STARTED", false)
    }


    fun initRecyclerView() {
        Thread {
            var todoList =
                AppDatabase.getInstance(this@ScrollingActivity).todoDao().getAllTodo()

            runOnUiThread {
                todoAdapter = TodoAdapter(this, todoList)

                //recyclerTodo.layoutManager = GridLayoutManager(this,2)
                var itemDecorator = DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL)
                recyclerTodo.addItemDecoration(itemDecorator)

                recyclerTodo.adapter = todoAdapter

                val callback = TodoReyclerTouchCallback(todoAdapter)
                val touchHelper = ItemTouchHelper(callback)
                touchHelper.attachToRecyclerView(recyclerTodo)
            }

        }.start()
    }


    var editIndex = -1

    fun showEditTodoDialog(todoToEdit: Todo, idx: Int) {
        editIndex = idx

        val editDialog = TodoDialog()

        val bundle = Bundle()
        bundle.putSerializable("KEY_TODO", todoToEdit)
        editDialog.arguments = bundle

        editDialog.show(supportFragmentManager, "TAG_TODO_EDIT")
    }

    override fun todoCreated(todo: Todo) {
        Thread {
            var newId = AppDatabase.getInstance(this@ScrollingActivity).todoDao().addTodo(
                todo
            )

            todo.todoId = newId

            runOnUiThread{
                todoAdapter.addTodo(todo)
            }

        }.start()
    }

    override fun todoUpdated(todo: Todo) {
        Thread {
            AppDatabase.getInstance(this@ScrollingActivity).todoDao().updateTodo(todo)

            runOnUiThread {
                todoAdapter.updateTodoOnPosition(todo, editIndex)
            }
        }.start()
    }

}
