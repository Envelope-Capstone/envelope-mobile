package com.joesamyn.envelope.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.joesamyn.envelope.R
import com.joesamyn.envelope.databinding.AddEnvelopePopupBinding
import com.joesamyn.envelope.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEnvelopePopup: DialogFragment() {


    private lateinit var binding: AddEnvelopePopupBinding
    private val vm: HomeViewModel by activityViewModels<HomeViewModel>()

    /**
     * Inflate the layout for dialog view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddEnvelopePopupBinding.inflate(inflater)

        // List items (maybe store in different class)
        val items = arrayOf(
                getString(R.string.merchandise),
                getString(R.string.automotive),
                getString(R.string.education),
                getString(R.string.deptStores),
                getString(R.string.gas),
                getString(R.string.restaurants),
        )

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, items)
        binding.envelopeDropdown.setAdapter(adapter)

        // Configure onclick handlers
        setupOnClickHandlers()

        return binding.root
    }

    // Set the size of the view
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
    }



    private fun setupOnClickHandlers(){
        // Create Button click listener
        binding.createButton.setOnClickListener{
            val envelopeName = binding.envelopeDropdown.text.toString()
            vm.addEnvelope(envelopeName)
            findNavController().popBackStack()
        }
    }
}