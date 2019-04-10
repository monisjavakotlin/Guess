package com.monis.guess

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest{
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)
    @Test
    fun guessWrong(){
        val resourses = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret
        for(n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.number)).perform(clearText())
                onView(withId(R.id.number)).perform(typeText(n.toString()))
                onView(withId(R.id.ok_button)).perform(click())
                val message =
                    if (n < secret) resourses.getString(R.string.bigger)
                    else resourses.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resourses.getString(R.string.ok))).perform(click())
            }
        }
    }
}