package com.contacts

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(
    private var contacts: List<Contact>,
    private val onCallRequest: (String) -> Unit,
    private val onSmsRequest: (String, String) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textContactName)
        val numberTextView: TextView = view.findViewById(R.id.textContactNumber)
        val optionsImageView: ImageView = view.findViewById(R.id.imageViewOptions)
        val contactIconImageView: ImageView = view.findViewById(R.id.imageViewContactIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.numberTextView.text = contact.phoneNumber

        val firstLetter = contact.name.firstOrNull()?.uppercase() ?: "?"
        val iconColor = Color.parseColor("#D9D9D9")
        setCircularTextIcon(holder.contactIconImageView, firstLetter, iconColor)

        holder.optionsImageView.setOnClickListener {
            showPopupMenu(holder.optionsImageView, contact.phoneNumber)
        }
    }

    override fun getItemCount(): Int = contacts.size

    private fun showPopupMenu(view: View, phoneNumber: String) {
        val popup = PopupMenu(view.context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_contact_options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_call -> {
                    onCallRequest(phoneNumber)
                    true
                }
                R.id.action_sms -> {
                    onSmsRequest(phoneNumber, view.context.getString(R.string.sms_message))
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    fun update(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }

    private fun setCircularTextIcon(imageView: ImageView, text: String, iconColor: Int) {
        imageView.setImageDrawable(createTextDrawable(text, iconColor))
    }

    private fun createTextDrawable(text: String, iconColor: Int): Drawable {
        val textPaint = Paint().apply {
            color = Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = 50f
            typeface = Typeface.DEFAULT_BOLD
        }

        val diameter = 100
        val bitmap = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val circlePaint = Paint().apply {
            color = iconColor
            isAntiAlias = true
        }
        canvas.drawCircle(diameter / 2f, diameter / 2f, diameter / 2f, circlePaint)

        val x = diameter / 2f
        val y = diameter / 2f - (textPaint.descent() + textPaint.ascent()) / 2
        canvas.drawText(text, x, y, textPaint)

        return BitmapDrawable(Resources.getSystem(), bitmap)
    }
}
