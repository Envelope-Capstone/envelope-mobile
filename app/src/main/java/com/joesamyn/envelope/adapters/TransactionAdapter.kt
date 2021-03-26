package com.joesamyn.envelope.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joesamyn.envelope.databinding.TransactionItemBinding
import com.joesamyn.envelope.models.ClassifiedTransaction
import java.text.SimpleDateFormat
import java.util.*

class TransactionAdapter(private val data: List<ClassifiedTransaction>): RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(private val binding: TransactionItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: ClassifiedTransaction) {
            binding.transactionDateString = SimpleDateFormat("MM/dd/yyy").format(transaction.TransactionDate)
            binding.transaction = transaction
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflate = LayoutInflater.from(parent.context)
                val binding = TransactionItemBinding.inflate(layoutInflate, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // Return new instance of ViewHolder with the inflated view for list items
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        val transaction = data[position]
        holder.bind(transaction!!)
    }

    /**
     * Get the size of the list
     */
    override fun getItemCount(): Int = data.size


}