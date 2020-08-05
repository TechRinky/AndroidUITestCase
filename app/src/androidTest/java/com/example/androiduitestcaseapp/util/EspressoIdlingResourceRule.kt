package com.example.androiduitestcaseapp.util

import androidx.test.espresso.IdlingRegistry
import com.example.androiduitestcaseapp.EspressoIdlingResource
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * This class is responsible to register and unregister EspressoIdlingResource
 * EspressoIdlingResource - An idling resource represents an asynchronous operation whose results affect subsequent operations in a UI test.
 */
class EspressoIdlingResourceRule : TestWatcher(){


    private val idlingResource = EspressoIdlingResource.countingIdlingResource

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }
}



