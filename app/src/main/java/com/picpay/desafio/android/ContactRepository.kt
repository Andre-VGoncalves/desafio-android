package com.picpay.desafio.android
import retrofit2.Call

interface ContactRepository {
    suspend fun getContacts() : Call<List<User>>
}