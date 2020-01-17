package hu.ecity.todorecyclerview

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.app.AlertDialog
import android.content.Context
import android.widget.CheckBox
import android.widget.EditText
import hu.ecity.todorecyclerview.data.Todo
import kotlinx.android.synthetic.main.todo_dialog.view.*
import java.util.*

class TodoDialog : DialogFragment() {

    interface TodoHandler {
        fun todoCreated(todo: Todo)
    }

    lateinit var todoHandler: TodoHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TodoHandler) {
            todoHandler = context
        } else {
            throw RuntimeException(
                "The Activity does not implement the TodoHandler interface!"
            )
        }
    }

    lateinit var etTodoText: EditText
    lateinit var cbTodoDone: CheckBox

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("Todo dialog")
        val dialogView = requireActivity().layoutInflater.inflate(
            R.layout.todo_dialog, null
        )
        etTodoText = dialogView.etTodoText
        cbTodoDone = dialogView.cbTodoDone

        dialogBuilder.setView(dialogView)

        dialogBuilder.setNegativeButton("Cancel") {
            dialog, which ->
        }
        dialogBuilder.setPositiveButton("Add") {
            dialog, which ->

            // elem létrehozása
            var newTodo = Todo(
                Date(System.currentTimeMillis()).toString(),
                cbTodoDone.isChecked,
                etTodoText.text.toString()
            )

            todoHandler.todoCreated(newTodo)
        }

        return dialogBuilder.create()
    }

}