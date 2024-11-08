package com.contacts.Fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CallLog
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contacts.R
import com.contacts.CallLogEntry
import com.contacts.CallLogAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.text.SimpleDateFormat
import java.util.*

class RecentFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CallLogAdapter

    private val requestPermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        if (permissions[Manifest.permission.READ_CALL_LOG] == true && permissions[Manifest.permission.READ_CONTACTS] == true) {
            loadCallLogs()
        } else {
            Toast.makeText(context, "Permissions required to show call logs", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recent, container, false)
        setupRecyclerView(view)
        checkPermissions()
        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.callLogRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CallLogAdapter(emptyList())
        recyclerView.adapter = adapter
    }

    private fun checkPermissions() {
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.READ_CALL_LOG)
        }

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.READ_CONTACTS)
        }

        if (permissionsToRequest.isEmpty()) {
            loadCallLogs()
        } else {
            requestPermissionsLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }

    private fun loadCallLogs() {
        val callLogs = mutableListOf<Any>()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val today = dateFormat.format(Date())
        var currentDate = ""

        val projection = arrayOf(
            CallLog.Calls.DATE,
            CallLog.Calls.NUMBER,
            CallLog.Calls.CACHED_NAME,
            CallLog.Calls.TYPE
        )

        val sortOrder = "${CallLog.Calls.DATE} DESC"

        context?.contentResolver?.query(
            CallLog.Calls.CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val dateColumn = cursor.getColumnIndex(CallLog.Calls.DATE)
                val numberColumn = cursor.getColumnIndex(CallLog.Calls.NUMBER)
                val nameColumn = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)

                val date = cursor.getLong(dateColumn)
                val formattedDate = dateFormat.format(Date(date))
                val number = cursor.getString(numberColumn)
                val name = cursor.getString(nameColumn) ?: resources.getString(R.string.name_contact)

                if (formattedDate != currentDate) {
                    currentDate = formattedDate
                    callLogs.add(if (formattedDate == today) resources.getString(R.string.date_recent) else formattedDate)
                }

                callLogs.add(CallLogEntry(name, number, formattedDate, Date(date)))
            }
        }

        activity?.runOnUiThread {
            adapter = CallLogAdapter(callLogs)
            recyclerView.adapter = adapter
        }
    }
}