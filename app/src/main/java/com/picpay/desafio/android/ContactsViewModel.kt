package com.picpay.desafio.android

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.network.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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