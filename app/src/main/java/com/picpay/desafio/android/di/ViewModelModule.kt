package com.picpay.desafio.android.di

import com.picpay.desafio.android.repository.ContactRepository
import com.picpay.desafio.android.repository.ContactRepositoryImpl
import com.picpay.desafio.android.feature.contacts.ContactsViewModel
import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.network.RetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsViewModel = module(override = true) {
    factory { RetrofitService.getService() }
    factory { ContactRepositoryImpl(service = get()) as ContactRepository }
    viewModel {
        ContactsViewModel(contactRepository = get())
    }
}

