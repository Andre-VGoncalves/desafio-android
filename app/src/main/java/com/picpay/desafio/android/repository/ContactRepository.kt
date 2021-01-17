package com.picpay.desafio.android.repository
import com.picpay.desafio.android.model.User

interface ContactRepository {
    suspend fun getContacts() : List<User>
}