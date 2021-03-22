package com.joesamyn.envelope.models

import android.graphics.drawable.Drawable

// TODO: Change icon to be bitmap, add List<Transaction> property

data class Envelope(var id: Int, var name: String, var icon: Int, var total: Double)
