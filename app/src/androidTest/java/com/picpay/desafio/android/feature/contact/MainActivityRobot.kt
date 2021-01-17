package com.picpay.desafio.android.feature.contact

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.picpay.desafio.android.BasePrepareRobot
import com.picpay.desafio.android.BaseValidateRobot
import com.picpay.desafio.android.feature.contacts.MainActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers

class MainActivityPrepare : BasePrepareRobot(){
    fun initView() {
        launchActivity<MainActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)
        }
    }

    fun startServer () {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> MockServer.successResponse
                    else -> MockServer.errorResponse
                }
            }
        }
        initServer()
    }

}

class MainActivityValidate : BaseValidateRobot(){
    fun descriptionVisible (description : String) {
        onView(ViewMatchers.withText(description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun elementNotVisible (id: Int) {
        onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    fun elementVisible (id: Int) {
        onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun startDelay (delay: Long) {
        onView(ViewMatchers.isRoot()).perform(waitFor(delay))
    }
}
