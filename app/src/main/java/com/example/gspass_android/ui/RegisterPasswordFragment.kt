package com.example.gspass_android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gspass_android.R

class RegisterPasswordFragment: Fragment() {

    lateinit var password : EditText
    lateinit var checkPassword : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view : View = inflater.inflate(R.layout.fragment_password, container,false)

        password = view.findViewById(R.id.password)
        checkPassword = view.findViewById(R.id.check_password)

        return view
    }
    fun getPassword() =
        password.text.toString()

    fun getCheckPassword() =
        checkPassword.text.toString()
}