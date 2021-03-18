package com.joesamyn.envelope.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.joesamyn.envelope.R
import com.joesamyn.envelope.models.Envelope

/**
 * Responsible for adapting a list of Envelope models into a listview on screen
 */
class EnvelopeAdapter(private val context: Context, private val data: List<Envelope>)
    :RecyclerView.Adapter<EnvelopeAdapter.EnvelopeViewHolder>(){

    /**
     * Holds the view elements of our item view we created for this RecyclerView
     */
    class EnvelopeViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val parentView: ConstraintLayout = view.findViewById(R.id.envelopeListViewItem)

    }

    /**
     * Create new views to be used in the RecyclerView
     * @return reference to envelope_item views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnvelopeViewHolder {
        // Obtain layout inflater from context and inflate the view
        // This will allow the adapterLayout variable to hold a reference to the envelope_item.xml view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.envelope_item, parent, false)

        return EnvelopeViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view with the proper data
     */
    override fun onBindViewHolder(holder: EnvelopeViewHolder, position: Int) {
        // get envelope item at the integer position
        val envelope = data[position]

        // Set the icon for the envelope
        holder.parentView.findViewById<ImageView>(R.id.envelopeIcon).setImageResource(envelope.icon)

        // set the name for the envelope
        holder.parentView.findViewById<TextView>(R.id.envelopeName).text = envelope.name

        // Set the total amount spent in this envelope
        holder.parentView.findViewById<TextView>(R.id.envelopeTotal).text = envelope.total.toString()
    }

    /**
     * Return size of list
     */
    override fun getItemCount(): Int {
        return data.size
    }
}