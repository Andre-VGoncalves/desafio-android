package com.picpay.desafio.android.di

import com.picpay.desafio.android.ContactRepository
import com.picpay.desafio.android.ContactRepositoryImpl
import com.picpay.desafio.android.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsViewModel = module(override = true) {
    factory {
        ContactRepositoryImpl() as ContactRepository
    }
    viewModel {
        ContactsViewModel(contactRepository = get())
    }
}

