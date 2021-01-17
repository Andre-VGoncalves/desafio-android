package com.picpay.desafio.android.feature.contacts

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.repository.ContactRepository
import com.picpay.desafio.android.base.BaseViewModel
import kotlinx.coroutines.launch

class ContactsViewModel (private val contactRepository: ContactRepository) : BaseViewModel() {
    val command = MutableLiveData<ContactsCommand>()

    fun requestContacts () {
        launch {
            try {
                val response = contactRepository.getContacts()
                command.value = ContactsCommand.ShowContacts(response)
            } catch (exception: Exception){
                command.value = ContactsCommand.ShowError(error = true)

            }
        }
    }
}