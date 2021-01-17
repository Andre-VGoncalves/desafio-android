package com.picpay.desafio.android.feature.contacts

import com.picpay.desafio.android.model.User

sealed class ContactsCommand {
    data class ShowContacts(val contactList: List<User>): ContactsCommand()
    data class ShowError(val error : Boolean): ContactsCommand()
}