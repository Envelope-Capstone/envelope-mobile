package com.joesamyn.envelope.repositories.envelope

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import com.joesamyn.envelope.R
import com.joesamyn.envelope.interfaces.repositories.IEnvelopeRepository
import com.joesamyn.envelope.models.Envelope
import javax.inject.Inject

class EnvelopeRepository @Inject constructor(): IEnvelopeRepository {

    // TODO: Change the Envelope to use Bitmap instead of int for icon
    // TODO: Add list of transactions as property to envelope

    /**
     * Get all envelopes from SQLite storage
     */
    override fun getEnvelopes(): List<Envelope> {
        return listOf(
                Envelope("Gas", R.drawable.gas_classification, 130.60),
                Envelope("Grocery", R.drawable.gas_classification, 130.60),
                Envelope("Shopping", R.drawable.gas_classification, 130.60),
                Envelope("Coffee", R.drawable.gas_classification, 130.60)
        )
    }
}