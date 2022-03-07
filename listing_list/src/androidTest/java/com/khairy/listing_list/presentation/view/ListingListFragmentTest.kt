package com.khairy.listing_list.presentation.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.khairy.core.test_utils.EspressoIdlingResource
import com.khairy.listing_list.R
import com.khairy.listing_list.presentation.adapters.ProductsAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4ClassRunner::class)
class ListingListFragmentTest {

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_isListVisible_onAppLaunch() {
        val launchFragmentInContainer = launchFragmentInContainer<ProductsFragment>()
        launchFragmentInContainer.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.rv_listings)).check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar_listing_list)).check(matches(not(isDisplayed())))
    }


    @Test
    fun test_navigation_to_details() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // Create a graphical FragmentScenario for the TitleScreen
        val fs = launchFragmentInContainer<ProductsFragment>()

        fs.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.main_navigation)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.rv_listings))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsAdapter.ListingViewHolder>(
                    1,
                    ViewActions.click()
                )
            )
        assertEquals(navController.currentDestination?.id, R.id.listingDetailsFragment)

    }
}
