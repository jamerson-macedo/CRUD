package com.example.crudcomplete.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudcomplete.R
import com.example.crudcomplete.data.db.repository.SubscriberRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberStateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addSubscriber(name: String, email: String) = viewModelScope.launch {
        try {
            val id = repository.insertSubscriber(name, email)
            if (id > 0) {
                _subscriberStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscriber_sucess
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.error
            Log.e(TAG, ex.toString())
        }
    }

    sealed class SubscriberState {
        object Inserted : SubscriberState()
    }

    companion object {
        private val TAG = SubscriberViewModel::class.java.simpleName
    }
}