package hu.ecity.databasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ecity.databasedemo.data.AppDatabase
import hu.ecity.databasedemo.data.Grade
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            saveGrade()
        }

        btnQuery.setOnClickListener {
            queryGrade()
        }
    }

    fun saveGrade() {
        Thread {
            var newGrade = Grade(
                null,
                etStudent.text.toString(),
                etGrade.text.toString()
            )
            AppDatabase.getInstance(this@MainActivity).gradeDao().
                insertGrades(newGrade)

        }.start()
    }

    fun queryGrade() {
        Thread {
            var gradesList = AppDatabase.getInstance(
                this@MainActivity).gradeDao().getSpecificGrades("5")

            runOnUiThread {
                tvData.text = ""
                gradesList.forEach {
                    tvData.append("${it.studentId} ${it.grade}\n")
                }
            }
        }.start()
    }

}
