package com.picpay.desafio.android

import com.picpay.desafio.android.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class ContactRepositoryImpl(private val service: PicPayService = RetrofitService.getService()) : ContactRepository{
    override suspend fun getContacts(): List<User> =
        withContext(Dispatchers.IO) {
            service.getUsers()
        }
}