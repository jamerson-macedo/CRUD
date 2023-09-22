package com.example.crudcomplete.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.example.crudcomplete.data.db.repository.SubscriberRepository

class SubscriberListViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val allSubscriberEvents=repository.getAllSubscribers()

}