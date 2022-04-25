package com.dionos.mystreamers.presentation.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dionos.features.followed_stream_list.data.source.FeaturesDataSource
import com.dionos.features.followed_stream_list.di.FeaturesModule
import com.dionos.features.followed_stream_list.presentation.view.FollowedStreamListFragment
import com.dionos.mystreamers.R
import com.dionos.mystreamers.launchFragmentInHiltContainer
import com.dionos.mystreamers.presentation.view.fake.FakeFeaturesNetworkDataSource
import com.dionos.mystreamers.presentation.view.fake.FakeUserSharedPreferencesDataSource
import com.dionos.user.data.source.UserDataSource
import com.dionos.user.di.UserModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Named

@UninstallModules(FeaturesModule::class, UserModule::class)
@HiltAndroidTest
class FollowedStreamListFragmentTest {

    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class UserModuleTest {

        @Binds
        @Named("UserSharedPreferencesDataSource")
        abstract fun bindUserSharedPreferencesDataSource(fakeUserSharedPreferencesDataSource: FakeUserSharedPreferencesDataSource): UserDataSource

        @Binds
        @Named("UserNetworkDataSource")
        abstract fun bindUserNetworkDataSource(fakeUserSharedPreferencesDataSource: FakeUserSharedPreferencesDataSource): UserDataSource
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class FeaturesModuleTest {

        @Binds
        @Named("FeaturesNetworkDataSource")
        abstract fun bindFeaturesNetworkDataSource(fakeFeaturesNetworkDataSource: FakeFeaturesNetworkDataSource): FeaturesDataSource
    }


    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun test() {
        launchFragmentInHiltContainer<FollowedStreamListFragment>()

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }


}