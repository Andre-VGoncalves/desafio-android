package com.picpay.desafio.android.feature.contact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.feature.contacts.ContactsCommand
import com.picpay.desafio.android.feature.contacts.ContactsViewModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactsViewModelTest {

    @InjectMockKs
    lateinit var viewModel: ContactsViewModel

    @MockK
    lateinit var repository: ContactRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    init {
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel.coroutineContext = Dispatchers.Unconfined + SupervisorJob()
    }

    @Test
    fun `requestContacts() - sucesso da chamada` () = runBlocking {
        val response: List<User> = listOf(User(img = "", name = "", id = 0, username = ""))
        coEvery { repository.getContacts() } returns response

        viewModel.requestContacts()

        Assert.assertEquals(viewModel.command.value, ContactsCommand.ShowContacts(response))
    }

    @Test
    fun `requestContacts() - erro da chamada` () = runBlocking {
        val exception : Exception = mockk()
        coEvery { repository.getContacts() } throws  exception

        viewModel.requestContacts()

        Assert.assertEquals(viewModel.command.value, ContactsCommand.ShowError(true))
    }

}