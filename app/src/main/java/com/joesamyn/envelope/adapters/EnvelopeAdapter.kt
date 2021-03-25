package com.joesamyn.envelope.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joesamyn.envelope.databinding.EnvelopeItemBinding
import com.joesamyn.envelope.models.Envelope

/**
 * Responsible for adapting a list of Envelope models into a listview on screen
 */
class EnvelopeAdapter(private val context: Context,
                      private val data: List<Envelope>,
                      private val clickListener: EnvelopeListener)
    :RecyclerView.Adapter<EnvelopeAdapter.EnvelopeViewHolder>(){

    /**
     * Holds the view elements of our item view we created for this RecyclerView
     */
    class EnvelopeViewHolder(private val binding: EnvelopeItemBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(item: Envelope, clickListener: EnvelopeListener){
            binding.envelope = item
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): EnvelopeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EnvelopeItemBinding.inflate(layoutInflater, parent, false)
                return EnvelopeViewHolder(binding)
            }
        }
    }

    /**
     * Create new views to be used in the RecyclerView
     * @return reference to envelope_item views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnvelopeViewHolder {

        return EnvelopeViewHolder.from(parent)
    }

    /**
     * Replace the contents of a view with the proper data
     */
    override fun onBindViewHolder(holder: EnvelopeViewHolder, position: Int) {
        // get envelope item at the integer position
        val envelope = data[position]
        holder.bind(envelope!!, clickListener)
    }

    /**
     * Return size of list
     */
    override fun getItemCount(): Int {
        return data.size
    }
}

class EnvelopeListener(val clickListener: (envelopeName: String) -> Unit){
    fun onClick(envelope: Envelope) = clickListener(envelope.name)
}