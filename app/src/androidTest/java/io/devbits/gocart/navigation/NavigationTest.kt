package io.devbits.gocart.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import io.devbits.gocart.core.data.UserPreferences
import io.devbits.gocart.core.data.dataStore
import org.junit.Before
import org.junit.Rule

/**
 * Tests all the navigation flows that are handled by navigation compose.
 */
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupGoCartNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            GoCartNavHost(
                navController = navController,
                startDestination = "Home",
                preferences = UserPreferences(LocalContext.current.dataStore),
            )
        }
    }

}
