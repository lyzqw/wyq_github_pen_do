package com.wyq_github_pen_do

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.wyq.common.base.BaseViewHolder
import com.wyq_github_pen_do.activity.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FirstTest {

    companion object {
        const val TAG = "FirstTest"
    }

    lateinit var noteDetailActivity: MainActivity

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
//        log 无效
//        ShadowLog.stream = System.out
        noteDetailActivity = activityRule.activity
    }

    /**
     * 创建Activity测试
     */
    @Test
    fun testMainActivity() {
        Assert.assertNotNull(noteDetailActivity)
        Log.d(TAG, "测试Log输出")
    }


    @Test
    fun scrollToItem_checkItsText() {
        onView(withId(R.id.recycler_main)).perform(
            RecyclerViewActions.actionOnItemAtPosition<BaseViewHolder>(
                2,
                click()
            )
        )

        onView(withText("itemElementText")).check(matches(isDisplayed()));
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.wyq_github_pen_do", appContext.packageName)
    }

    @Test
    fun testAssertNotNull() {
        Assert.assertNull("should be null", null)
    }

    @Test
    fun testEditNote() {
        onView(withId(R.id.noteTitle)).perform(ViewActions.replaceText("测试title"))
        onView(withText("NEW 测试title")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}