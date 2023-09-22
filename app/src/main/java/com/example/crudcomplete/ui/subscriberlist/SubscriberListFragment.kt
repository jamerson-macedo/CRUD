package com.example.crudcomplete.ui.subscriberlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crudcomplete.R
import com.example.crudcomplete.data.db.AppDataBase
import com.example.crudcomplete.data.db.dao.SubscriberDAO
import com.example.crudcomplete.data.db.repository.DatabaseDataSource
import com.example.crudcomplete.data.db.repository.SubscriberRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SubscriberListFragment : Fragment(R.layout.fragment_subscriber_list) {
    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDataBase.getinstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_subscriber)
         val button_fab: FloatingActionButton=view.findViewById(R.id.fab_button)

     observerviewmodelevents(recyclerView)
        configureviewListeners(button_fab)



    }

    private fun observerviewmodelevents(recyclerView: RecyclerView) {
        viewModel.allSubscriberEvents.observe(viewLifecycleOwner, Observer {allsubscribers->
            val subscriberListAdapter=SubscriberListAdapter(allsubscribers)
            with(recyclerView) {
                setHasFixedSize(true)
                adapter = subscriberListAdapter

            }
        })

        // seta tudo de uma vez


    }
    private fun configureviewListeners(floatingActionButton: FloatingActionButton){
       floatingActionButton.setOnClickListener{

            findNavController().navigate(R.id.subscriberFragment)
        }
    }


}