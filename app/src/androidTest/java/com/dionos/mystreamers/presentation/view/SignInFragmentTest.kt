package com.dionos.mystreamers.presentation.view

import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import com.dionos.mystreamers.launchFragmentInHiltContainer
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
        onWebView()
            .withElement(findElement(Locator.ID, "login-username"))
            .perform(webKeys("di0n0s"))
            .withElement(findElement(Locator.ID, "password-input"))
            .perform(webKeys("divas060315"))
            .withElement(
                findElement(
                    Locator.XPATH,
                    "//*[@id=\"root\"]/div/div[1]/div[3]/div/div/div/div[3]/form/div/div[3]/button"
                )
            )
            .perform(webClick())

    }
}