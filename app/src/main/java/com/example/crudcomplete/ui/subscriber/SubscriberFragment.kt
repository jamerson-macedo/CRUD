package com.example.crudcomplete.ui.subscriber

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudcomplete.MainActivity
import com.example.crudcomplete.R
import com.example.crudcomplete.data.db.AppDataBase
import com.example.crudcomplete.data.db.dao.SubscriberDAO
import com.example.crudcomplete.data.db.repository.DatabaseDataSource
import com.example.crudcomplete.data.db.repository.SubscriberRepository
import com.example.crudcomplete.extension.hidekeyboard
import com.google.android.material.snackbar.Snackbar


class SubscriberFragment : Fragment() {
    private lateinit var input_name: EditText
    private lateinit var input_email: EditText
    private lateinit var button_subscriber: Button


    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDataBase.getinstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository) as T
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_subscribe, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        button_subscriber = view.findViewById(R.id.button_subscriber)
        input_email = view.findViewById(R.id.input_email)
        input_name = view.findViewById(R.id.input_name)

        observeEvents()
        setListeners()

    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                }

            }
        }
        clearFields()
        hideKeyboard()

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {

        input_name.text?.clear()
        input_email.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hidekeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener {
            val name = input_name.text.toString()
            val email = input_email.text.toString()

            viewModel.addSubscriber(name, email)
        }
    }


}