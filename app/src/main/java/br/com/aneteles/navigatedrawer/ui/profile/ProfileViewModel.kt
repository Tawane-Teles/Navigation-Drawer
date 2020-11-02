package br.com.aneteles.navigatedrawer.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Essa tela é facebook"
    }
    val text: LiveData<String> = _text
}