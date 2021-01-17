package com.picpay.desafio.android

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactsViewModel (private val contactRepository: ContactRepository) : ViewModel() {
    val command = MutableLiveData<ContactsCommand>()

    fun requestContacts () {
        Log.e("Andre", contactRepository.toString())
        RetrofitService.getService().getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    command.value = ContactsCommand.ShowError(error = true)
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    command.value = response.body()?.let { ContactsCommand.ShowContacts(it) }
                }
            })
    }
}