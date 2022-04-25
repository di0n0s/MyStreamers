package com.dionos.mystreamers.presentation.view

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.web.model.SimpleAtom
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import com.dionos.mystreamers.R
import com.dionos.mystreamers.launchFragmentInHiltContainer
import com.dionos.mystreamers.presentation.view.util.EspressoUtils.waitFor
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
        //TODO this is not working because Atom<Evaluation>.webKeys doesn´t trigger enable login button. I tried to trigger with js function but I couldn´t. Other user has same issue here https://stackoverflow.com/questions/58075087/android-espresso-webview-webclick-and-webkeys-does-not-trigger-blur-event-on.
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        launchFragmentInHiltContainer<SignInFragment> {
            // In addition to returning a new instance of our Fragment,
            // get a callback whenever the fragment’s view is created
            // or destroyed so that we can set the mock NavController
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    navController.setGraph(R.navigation.nav_graph)
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
        onWebView()
            .withElement(findElement(Locator.ID, "login-username"))
            .perform(webKeys("my_streamer_test"))
            .withElement(findElement(Locator.ID, "password-input"))
            .perform(webKeys("vhP&uhOzT5Hf"))
            .perform(
                SimpleAtom(
                    "function(elem) {\n" +
                            "var e = document.createEvent('Event');\n" +
                            "e.initEvent('change', false, true);\n" +
                            "elem.dispatchEvent(e);}"
                )
            )

        onView(isRoot()).perform(waitFor(10000))


        onWebView()
            .withElement(
                findElement(
                    Locator.XPATH,
                    "//*[@id=\"root\"]/div/div[1]/div[3]/div/div/div/div[3]/form/div/div[3]/button"

                )

            )
            .perform(webClick())


//        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

    }
}