package com.example.androiduitestcaseapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.androiduitestcaseapp.util.EspressoIdlingResourceRule
import com.example.techmassignment.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    val titleText  = "About Canada"

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @get:Rule
    val espressoIdlingResource = EspressoIdlingResourceRule()


    /**
     * This test is responsible to test main view is displayed or not
     * Listview is displayed or not
     * Toolbar Title text is set after getting data from json
     */
    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.data_listview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.toolbar_title_tv)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    titleText
                )
            )
        )
    }
}