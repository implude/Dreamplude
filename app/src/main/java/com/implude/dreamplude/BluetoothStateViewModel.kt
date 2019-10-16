package com.implude.dreamplude

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class BluetoothStateViewModel : ViewModel() {
    val isDiscovering = ObservableField<Boolean>(false)
}