package com.joesamyn.envelope.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.joesamyn.envelope.R
import com.joesamyn.envelope.adapters.EnvelopeAdapter
import com.joesamyn.envelope.adapters.EnvelopeListener
import com.joesamyn.envelope.adapters.TransactionAdapter
import com.joesamyn.envelope.databinding.FragmentTransactionBinding
import com.joesamyn.envelope.models.ClassifiedTransaction
import com.joesamyn.envelope.models.Envelope
import com.joesamyn.envelope.ui.viewmodels.TransactionStateEvent
import com.joesamyn.envelope.ui.viewmodels.TransactionViewModel
import com.joesamyn.envelope.util.DataState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private val TAG = TransactionFragment::class.java.simpleName

    private lateinit var binding: FragmentTransactionBinding
    private val viewModel: TransactionViewModel by viewModels<TransactionViewModel>()
    private val args: TransactionFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_transaction,
            container,
            false)

        // Subscribe observers
        subscribeObservers()

        // Initially load page
        // get envelope from args
        val env = args.envelopeName
        viewModel.setStateEvent(TransactionStateEvent.GetTransactionsEvent, env)


        return binding.root
    }

    fun subscribeObservers() {
        viewModel.transaction.observe(viewLifecycleOwner, Observer { dataState ->
            // Handle proper state events
            when(dataState) {
                // Handle success state (data returned)
                is DataState.Success<List<ClassifiedTransaction>> -> {
                    displayProgressBar(false)
                    displayTransactions(dataState.data)
                }

                // Handle exception thrown
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                // Handle loading state (show activity icon)
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    /**
     * Handle error and display proper message
     */
    private fun displayError(message: String?){
        if(message != null){
            Log.e(TAG, message)
        }
        else {
            Log.e(TAG, "unknown error occured")
        }
    }

    /**
     * Handle progress bar view for when items are loading
     */
    private fun displayProgressBar(isDisplay: Boolean){
        if(isDisplay)
            binding.transactionsLoadingIndicator.show()
        else
            binding.transactionsLoadingIndicator.hide()
    }

    /**
     * Display the envelopes from repository get method
     */
    private fun displayTransactions(transactions: List<ClassifiedTransaction>){
        binding.transactionListView.adapter = TransactionAdapter(transactions)
    }

    companion object {

        @JvmStatic
        fun newInstance(): TransactionFragment {
            return TransactionFragment()
        }
    }
}