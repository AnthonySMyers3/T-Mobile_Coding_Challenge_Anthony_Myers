package com.example.t_mobilecodingchallengeanthonymyers

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.t_mobilecodingchallengeanthonymyers", appContext.packageName)
    }

    /*
    * Given more time I would set up UI tests here. This would include checking if the progress bar visibility
    * is set up correctly depending on the current loading state of data and images. Checking to see if data
    * is binding to the recycler view correctly by creating mock data.
    * */
}