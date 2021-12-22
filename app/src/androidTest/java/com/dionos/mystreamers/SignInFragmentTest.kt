package com.dionos.mystreamers

import com.dionos.user.presentation.view.SignInFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SignInFragmentTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun singIn() {
        launchFragmentInHiltContainer<SignInFragment>()
//        onWebView()
//            .withElement(DriverAtoms.findElement(Locator.ID, "login-username"))
//            .perform(DriverAtoms.webKeys("di0n0s"))
    }
}