package com.example.gspass_android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gspass_android.R

class RegisterUserInfoFragment : Fragment() {

    lateinit var schoolYear : EditText
    lateinit var userGcn : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view : View = inflater.inflate(R.layout.fragment_user_info, container,false)
        schoolYear = view.findViewById(R.id.school_year)
        userGcn = view.findViewById(R.id.user_gcn)
        return view
    }
    fun getSchoolYear() =
        schoolYear.text.toString()

    fun getUserGcn() =
        userGcn.text.toString()

}