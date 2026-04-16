package com.infophone.home.presentation.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infophone.home.domain.usecase.GetChatUseCase
import com.infophone.xmpp.XmppSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val xmppSessionManager: XmppSessionManager
) : ViewModel() {
    private val _itemClickedEvent = MutableSharedFlow<Int>()
    val itemClickedEvent: SharedFlow<Int> = _itemClickedEvent.asSharedFlow()

    init {
        Log.d("ChatViewModel", "ChatViewModel init")
        viewModelScope.launch {
            xmppSessionManager.start("9731581515", "Anu26@enso")
        }
    }

    fun onItemClicked(index: Int) {
        viewModelScope.launch {
            _itemClickedEvent.emit(index)
        }
    }
}