package com.example.crudcomplete.ui.subscriberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudcomplete.R
import com.example.crudcomplete.data.db.entity.SubscriberEntity

class SubscriberListAdapter(
    private val subscribers: List<SubscriberEntity>
) : RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_subscriber, parent, false)

        return SubscriberListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscribers[position])
    }

    override fun getItemCount(): Int = subscribers.size

    class SubscriberListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewSubscriberName: TextView = itemView.findViewById(R.id.subscribername)
        private val textViewSubscriberEmail: TextView = itemView.findViewById(R.id.subscriberemail)

        fun bindView(subscriber: SubscriberEntity) {
            textViewSubscriberName.text = subscriber.name
            textViewSubscriberEmail.text = subscriber.emaIL
        }
    }
}