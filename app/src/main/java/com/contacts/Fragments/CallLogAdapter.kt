package com.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class CallLogAdapter(private var data: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CALL_LOG = 1
        private const val VIEW_TYPE_DATE_HEADER = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is String) VIEW_TYPE_DATE_HEADER else VIEW_TYPE_CALL_LOG
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DATE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_date_header, parent, false)
                DateHeaderViewHolder(view)
            }
            VIEW_TYPE_CALL_LOG -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_call_log, parent, false)
                CallLogViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateHeaderViewHolder -> {
                val dateHeader = data[position] as String
                holder.bind(dateHeader)
            }
            is CallLogViewHolder -> {
                val callLogEntry = data[position] as CallLogEntry
                holder.bind(callLogEntry)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    inner class DateHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateHeader: TextView = itemView.findViewById(R.id.dateHeader)

        fun bind(date: String) {
            dateHeader.text = date
        }
    }

    inner class CallLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactName: TextView = itemView.findViewById(R.id.contactName)
        private val phoneNumber: TextView = itemView.findViewById(R.id.phoneNumber)
        private val timeRecent: TextView = itemView.findViewById(R.id.time_recent)

        fun bind(entry: CallLogEntry) {
            contactName.text = entry.name
            phoneNumber.text = entry.phoneNumber
            timeRecent.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(entry.callDate)
        }
    }
}

