package com.ctwofinalproject.ticketing.admin.util

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

object ShowSnack {
    fun show(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}