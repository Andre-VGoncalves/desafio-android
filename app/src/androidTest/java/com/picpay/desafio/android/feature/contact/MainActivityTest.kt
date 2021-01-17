package com.picpay.desafio.android.feature.contact

import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import org.junit.Test

class MainActivityTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun prepare(func: MainActivityPrepare.() -> Unit) {
        MainActivityPrepare(
        ).apply(func)
    }
    private fun validate (func: MainActivityValidate.() -> Unit) {
        MainActivityValidate(
        ).apply(func)
    }

    @Test
    fun shouldDisplayTitle() {
        val expectedTitle = context.getString(R.string.title)

        prepare { initView() }
        validate { descriptionVisible(expectedTitle) }
    }

    @Test
    fun shouldDisplayListItem() {
        prepare {
            startServer()
            initView()
        }

        validate {
            elementNotVisible(R.id.recyclerView)
            startDelay(5000)
            elementVisible(R.id.recyclerView)
            descriptionVisible("Marina Coelho")
        }

        prepare { stopServer() }
    }
}