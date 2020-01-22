package hu.ecity.todorecyclerview.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) var todoId: Long?,
    @ColumnInfo(name = "createdate") var createDate: String,
    @ColumnInfo(name = "done") var done: Boolean,
    @ColumnInfo(name = "todotext") var todoText: String,
    @ColumnInfo(name = "category") var category: Int
) : Serializable
{
    companion object {
        const val CATEGORY_WORK = 1
        const val CATEGORY_FAMILY = 2
        const val CATEGORY_OTHERS = 3
    }
}