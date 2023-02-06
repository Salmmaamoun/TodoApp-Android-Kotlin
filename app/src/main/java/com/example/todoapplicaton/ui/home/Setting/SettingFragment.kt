package com.example.todoapplicaton.ui.home.Setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapplicaton.R
import com.example.todoapplicaton.databinding.FragmentListBinding
import com.example.todoapplicaton.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    lateinit var viewBinding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewBinding= FragmentSettingBinding.inflate(inflater,container,false)
        return viewBinding.root
    }



}