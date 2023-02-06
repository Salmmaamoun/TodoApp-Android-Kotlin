package com.example.todoapplicaton.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todoapplicaton.R
import com.example.todoapplicaton.databinding.ActivityMainBinding
import com.example.todoapplicaton.ui.home.Setting.SettingFragment
import com.example.todoapplicaton.ui.home.list.ListFragments


class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    var listFragment=ListFragments()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.bottomNav.selectedItemId = R.id.nav_tasks_list
        viewBinding.screenTitle.setText(R.string.title_setting)
        showFragment(listFragment)
        viewBinding.addTask.setOnClickListener {
            val addBottomSheet = AddBottomSheetFragment()
         addBottomSheet.onAddClick=object :AddBottomSheetFragment.OnAddClicked{
                override fun onClick() {
                    listFragment.refreshTodoaas()

                }

            }
addBottomSheet.show(supportFragmentManager,"")
        }

        viewBinding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_settings -> {
                    showFragment(SettingFragment())

                }
                R.id.nav_tasks_list -> {
                    viewBinding.screenTitle.setText(R.string.title_task_list)
                    showFragment(listFragment)
                }


            }
            return@setOnItemSelectedListener true
        }

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).commit()
    }

}