package com.picpay.desafio.android

sealed class ContactsCommand {
    data class ShowContacts(val contactList: List<User>): ContactsCommand()
    data class ShowError(val error : Boolean): ContactsCommand()
}