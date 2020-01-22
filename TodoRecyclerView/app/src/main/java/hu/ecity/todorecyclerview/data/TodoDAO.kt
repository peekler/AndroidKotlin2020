package hu.ecity.todorecyclerview.data

import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todo")
    fun getAllTodo() : List<Todo>

    @Insert
    fun addTodo(todo: Todo): Long

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)
}