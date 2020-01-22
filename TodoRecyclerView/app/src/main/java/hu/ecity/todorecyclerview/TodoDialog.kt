package hu.ecity.todorecyclerview

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.app.AlertDialog
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import hu.ecity.todorecyclerview.data.Todo
import kotlinx.android.synthetic.main.todo_dialog.view.*
import java.util.*

class TodoDialog : DialogFragment() {

    interface TodoHandler {
        fun todoCreated(todo: Todo)
        fun todoUpdated(todo: Todo)
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
    lateinit var spinnerCategory: Spinner

    var isEditMode = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("Todo dialog")
        val dialogView = requireActivity().layoutInflater.inflate(
            R.layout.todo_dialog, null
        )
        etTodoText = dialogView.etTodoText
        cbTodoDone = dialogView.cbTodoDone
        spinnerCategory = dialogView.spinnerCategory

        var categoryAdapter = ArrayAdapter.createFromResource(
            context!!,
            R.array.category_array,
            android.R.layout.simple_spinner_item
        )
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = categoryAdapter


        dialogBuilder.setView(dialogView)

        isEditMode = ((arguments != null) && arguments!!.containsKey("KEY_TODO"))
        if (isEditMode) {
            dialogBuilder.setTitle("Edit todo")

            var todo: Todo = (arguments?.getSerializable("KEY_TODO") as Todo)
            etTodoText.setText(todo.todoText)
            cbTodoDone.isChecked = todo.done

            spinnerCategory.setSelection(todo.category-1)
        }

        dialogBuilder.setNegativeButton("Cancel") {
            dialog, which ->
        }
        dialogBuilder.setPositiveButton(if(isEditMode) "Update" else "Add") {
            dialog, which ->

        }

        return dialogBuilder.create()
    }


    override fun onResume() {
        super.onResume()

        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etTodoText.text.isNotEmpty()) {
                if (isEditMode) {
                    handleTodoEdit()
                } else {
                    handleTodoCreate()
                }
                (dialog as AlertDialog).dismiss()
            } else {
                etTodoText.error = "This field can not be empty"
            }
        }
    }

    private fun handleTodoCreate() {
        todoHandler.todoCreated(
            Todo(
                null,
                Date(System.currentTimeMillis()).toString(),
                cbTodoDone.isChecked,
                etTodoText.text.toString(),
                spinnerCategory.selectedItemPosition+1
            )
        )
    }

    private fun handleTodoEdit() {
        val todoToEdit = arguments?.getSerializable(
            "KEY_TODO"
        ) as Todo
        todoToEdit.todoText = etTodoText.text.toString()
        todoToEdit.done = cbTodoDone.isChecked

        todoToEdit.category = spinnerCategory.selectedItemPosition+1

        todoHandler.todoUpdated(todoToEdit)
    }

}