package com.example.todoapplicaton.ui.home

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.todoapplicaton.R
import com.example.todoapplicaton.database.MyDataBase
import com.example.todoapplicaton.database.model.Task
import com.example.todoapplicaton.databinding.FragmentAddBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var viewBinding: FragmentAddBottomSheetBinding
    var selectedDateCalendar: Calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAddBottomSheetBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upDateTv()

        initLisner()


    }

    fun upDateTv() {
        viewBinding.selectedDate.text =
            "${selectedDateCalendar.get(Calendar.DAY_OF_MONTH) }//${selectedDateCalendar.get(Calendar.MONTH)+1}//${
                selectedDateCalendar.get(Calendar.YEAR)
            }"
    }

    fun initLisner() {
        viewBinding.selectDateTv.setOnClickListener {
            val dialog = DatePickerDialog(
                requireActivity(),
                object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
                        selectedDateCalendar.set(year,month,day)
                        upDateTv()

                    }
                },
                selectedDateCalendar.get(Calendar.YEAR),
                selectedDateCalendar.get(Calendar.MONTH),
                selectedDateCalendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()


        }
        viewBinding.addTodoBtn.setOnClickListener {
            if(! validate()) return@setOnClickListener
            selectedDateCalendar.clear(Calendar.HOUR)
            selectedDateCalendar.clear(Calendar.MINUTE)
            selectedDateCalendar.clear(Calendar.SECOND)
            selectedDateCalendar.clear(Calendar.MILLISECOND)
            if(!validate()) return@setOnClickListener
            val todo=Task(id ,title = viewBinding.titleTextInput.editText?.text.toString(),
            description = viewBinding.descriptionTextInput.editText?.text.toString(),
            isDone = false, date = selectedDateCalendar.time.time)
            MyDataBase.getDatabase(requireContext()).getTaskDao().insertTask(todo)
            onAddClick?.onClick()
            dismiss()


        }
    }
    var onAddClick:OnAddClicked?=null
    interface OnAddClicked{
        fun onClick()

    }

    fun validate(): Boolean {
        if (viewBinding.titleTextInput.editText!!.text.toString()=="") {
            viewBinding.titleTextInput.error = "pleas write todo title"
            return false
        } else {
            viewBinding.titleTextInput.error=null
        }
        if (viewBinding.descriptionTextInput.editText!!.text.toString()=="") {
            viewBinding.descriptionTextInput.error = "pleas write todo description"
            return false
        } else {
            viewBinding.descriptionTextInput.error=null

        }
        return true
    }


}