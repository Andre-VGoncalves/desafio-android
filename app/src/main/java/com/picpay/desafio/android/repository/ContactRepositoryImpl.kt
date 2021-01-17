package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactRepositoryImpl(private val service: PicPayService) : ContactRepository {
    override suspend fun getContacts(): List<User> =
        withContext(Dispatchers.IO) {
            service.getUsers()
        }
}