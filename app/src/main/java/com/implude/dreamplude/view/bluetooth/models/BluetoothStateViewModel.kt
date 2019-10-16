package com.implude.dreamplude.view.bluetooth.models

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class BluetoothStateViewModel : ViewModel() {
    val isDiscovering = ObservableField<Boolean>(false)
}