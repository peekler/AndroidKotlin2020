package hu.ecity.databasedemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grade(
    @PrimaryKey(autoGenerate = true) var gradeId: Long?,
    @ColumnInfo(name="studentid") var studentId: String,
    @ColumnInfo(name="grade") var grade: String
)