package com.example.gspass_android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gspass_android.R

class RegisterIdFragment: Fragment() {

     lateinit var id : EditText

     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View? {
          super.onCreateView(inflater, container, savedInstanceState)
          val view : View = inflater.inflate(R.layout.fragment_id, container,false)

          id = view.findViewById(R.id.id)

          return view
     }
     fun getPassId() =
          id.text.toString()
}