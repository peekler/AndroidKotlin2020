package hu.ecity.layoutinflaterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_row.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            addTodo()
        }
    }

    private fun addTodo() {
        var todoView = layoutInflater.inflate(
            R.layout.todo_row, null)

        todoView.tvTodo.text = etTodo.text.toString()

        todoView.btnDelete.setOnClickListener {
            layoutContent.removeView(todoView)
        }

        layoutContent.addView(todoView)
    }


}
