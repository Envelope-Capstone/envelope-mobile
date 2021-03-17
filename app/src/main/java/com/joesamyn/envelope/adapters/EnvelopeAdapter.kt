package com.joesamyn.envelope.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.joesamyn.envelope.R

/**
 * Responsible for adapting a list of Envelope models into a listview on screen
 */
class EnvelopeAdapter(private val context: Context, private val data: List<String>)
    :RecyclerView.Adapter<EnvelopeAdapter.EnvelopeViewHolder>(){

    /**
     * Holds the view elements of our item view we created for this RecyclerView
     */
    class EnvelopeViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val iconView: ImageView = view.findViewById(R.id.envelopeListViewItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnvelopeViewHolder {
        // Obtain layout inflater

    }

    override fun onBindViewHolder(holder: EnvelopeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    /**
     * Return size of list
     */
    override fun getItemCount(): Int {
        return data.size
    }
}