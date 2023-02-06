package com.example.todoapplicaton.ui.home.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplicaton.Adapters.OnItemClicked
import com.example.todoapplicaton.Adapters.OnItemDeleteClickd
import com.example.todoapplicaton.Adapters.TodosAdapter
import com.example.todoapplicaton.Constant
import com.example.todoapplicaton.R
import com.example.todoapplicaton.base.BaseFragment
import com.example.todoapplicaton.database.MyDataBase
import com.example.todoapplicaton.database.model.Task
import com.example.todoapplicaton.databinding.FragmentListBinding
import com.example.todoapplicaton.ui.EditActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.*


class ListFragments : BaseFragment() {
    lateinit var viewBinding: FragmentListBinding
    var selectedDay: Calendar = Calendar.getInstance()
    var adapter=TodosAdapter(listOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewBinding= FragmentListBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiView()
        initListener()
        refreshTodoaas()
    }
    fun intiView(){
        viewBinding.calendarView.selectedDate= CalendarDay.today()
        viewBinding.todoRecycleView.adapter=adapter

    }
    fun initListener(){
       viewBinding.calendarView.setOnDateChangedListener(object: OnDateSelectedListener {
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                selectedDay.set(date.year, date.month-1, date.day)
                refreshTodoaas()
            }
        })

        adapter.onItemClicked=object :OnItemClicked{
            override fun onItemClick(task: Task) {
                showMessage("what do you want to do?",
                    "Update",
                    {_,i->updateTodo(task)},
                    "make done",
                    {_,i->makeTodoDone(task)}
                )
            }

        }
       adapter.onItemDeleteClickd=object : OnItemDeleteClickd {


            override fun onItemDeleteClick(pos: Int, task: Task) {
                deleteTask(task)
            }

        }

    }

    private fun deleteTask(task: Task) {
        showMessage("Are you want to delete this task ? "
        ,"yes",
        posAction = {dialog,_->
            dialog.dismiss()
            MyDataBase.getDatabase(requireContext()).getTaskDao().deleteTask(task)
            refreshRecyclerView()
        }, negActionTitle = "Cancel",
            negAction = {dialog,_->
                dialog.dismiss()
            }
        )

    }

    private fun makeTodoDone(task: Task) {
        task.isDone=true
        MyDataBase.getDatabase(requireContext()).getTaskDao().updateTask(task)
        refreshRecyclerView()

    }

    private fun refreshRecyclerView() {
        adapter.changData(MyDataBase.getDatabase(requireContext()).getTaskDao().getAllTasks())
        adapter.notifyDataSetChanged()
    }

    private fun updateTodo(task: Task) {
        var intent=Intent(requireActivity(),EditActivity::class.java)
        intent.putExtra(Constant.TASK,task)
        startActivity(intent)

    }

    fun refreshTodoaas(){
     selectedDay.clear(Calendar.HOUR)
     selectedDay.clear(Calendar.MINUTE)
     selectedDay.clear(Calendar.SECOND)
     selectedDay.clear(Calendar.MILLISECOND)
     val todos = MyDataBase.getDatabase(requireContext()).getTaskDao().getTodosByDate(
         selectedDay.time.time)
     adapter.changData(todos)
 }


}