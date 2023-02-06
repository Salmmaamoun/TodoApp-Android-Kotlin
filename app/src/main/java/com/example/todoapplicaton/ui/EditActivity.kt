package com.example.todoapplicaton.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.example.todoapplicaton.Constant.Companion.TASK
import com.example.todoapplicaton.database.MyDataBase
import com.example.todoapplicaton.database.model.Task
import com.example.todoapplicaton.databinding.ActivityEditBinding
import com.example.todoapplicaton.ui.home.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    lateinit var task:Task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        task= (intent.getSerializableExtra(TASK)as Task)

        showData(task)
        binding.submit.setOnClickListener {
            updateTodo()
        }

    }

    private fun updateTodo() {
        if(isDataValid()){
            task.title=binding.titleContainer.editText?.text.toString()
            task.description=binding.desContainer.editText?.text.toString()
           // task.date=binding.dateContainer.editText?.text.toString().toLong()
            MyDataBase.getDatabase(this).getTaskDao().updateTask(task)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun isDataValid(): Boolean {
        var isValid=true
        if(binding.titleContainer.editText?.text.toString()==""){
            binding.titleContainer.error="please, Enter title.."
            isValid=false
        }else{
            binding.titleContainer.error=null
        }

        if(binding.desContainer.editText?.text.toString()==""){
            binding.dateContainer.error="please, Enter description.."
            isValid=false
        }else{
            binding.desContainer.error=null
        }
        if(binding.dateContainer.editText?.text.toString()==""){
            binding.dateContainer.error="please, select Date.."
            isValid=false
        }else{
            binding.titleContainer.error=null
        }
        return  isValid
    }

    private fun showData(task: Task) {
        binding.titleContainer.editText?.setText(task.title)
        binding.desContainer.editText?.setText(task.description)
        var date =convertLong(task.date)
        binding.dateContainer.editText?.setText(date)

    }
    private fun convertLong(date: Long?):String? {
        val date=Date(date!!)
        val formate=SimpleDateFormat("yyyy.MM.dd HH:mm")
        return  formate.format(date)

    }
}