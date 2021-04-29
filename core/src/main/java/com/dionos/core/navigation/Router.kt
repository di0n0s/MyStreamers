package com.dionos.core.navigation

import androidx.fragment.app.FragmentManager

interface Router {
    var supportFragmentManager: FragmentManager

    fun showPendingFragment()
}