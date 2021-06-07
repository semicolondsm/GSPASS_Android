package com.example.gspass_android.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.gspass_android.R
import com.example.gspass_android.databinding.DialogMypageBinding

class MyPageDialog (
    private val context: Context,
    private val onMemoItemMenuDialogButtonListener: OnMemoItemMenuDialogButtonListener
) {

    interface OnMemoItemMenuDialogButtonListener {
        fun onChangePasswordClick()
        fun onLogoutClick()
        fun onCancelClick()
    }

    fun callDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding: DialogMypageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_mypage,
            null,
            false
        )
        dialog.setContentView(binding.root)

        binding.btnEditDialog.setOnClickListener {
            onMemoItemMenuDialogButtonListener.onChangePasswordClick()
            dialog.dismiss()
        }

        binding.btnDeleteDialog.setOnClickListener {
            onMemoItemMenuDialogButtonListener.onLogoutClick()
            dialog.dismiss()
        }

        binding.btnCancelMemoItemMenuDialog.setOnClickListener {
            onMemoItemMenuDialogButtonListener.onCancelClick()
            dialog.dismiss()
        }

        dialog.show()
    }
}