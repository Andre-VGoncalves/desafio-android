package com.picpay.desafio.android.feature.contacts

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private val viewModel: ContactsViewModel by viewModel()

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        setObservers()
        viewModel.requestContacts()
        progressBar.visibility = View.VISIBLE

    }
    private fun setObservers() {
        viewModel.command.observe(this, Observer {
            when (it) {
                is ContactsCommand.ShowContacts -> showContacts(it.contactList)
                is ContactsCommand.ShowError -> showError()
            }
        })
    }
    private fun showError() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT)
            .show()
    }

    private fun showContacts(contacts : List<User>) {
        progressBar.visibility = View.GONE

        adapter.users = contacts
    }
}
