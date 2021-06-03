package com.example.gspass_android.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gspass_android.R

class RegisterSchoolCodeFragment : Fragment() {

    lateinit var schoolCode : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view : View = inflater.inflate(R.layout.fragment_school_code, container,false)

        schoolCode = view.findViewById(R.id.code)

        return view
    }
    fun getSchoolCode() =
        schoolCode.text.toString()

}