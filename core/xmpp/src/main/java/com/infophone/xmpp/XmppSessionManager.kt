package com.infophone.xmpp

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class XmppSessionManager @Inject constructor(
    private val xmppClient: XmppClient
) {
    private val _session: MutableStateFlow<SessionState> = MutableStateFlow(SessionState.Idle)
    val session: StateFlow<SessionState> = _session.asStateFlow()

    suspend fun start(username: String, password: String) {
        if (session.value is SessionState.Connected) {
            return
        }
        _session.value = SessionState.Connecting
        runCatching {
            Log.d("XmppSessionManager", "Connecting to $username")
            xmppClient.connectAndLogin(username, password)
            xmppClient.setPresence(true, "Online")
            xmppClient.sendMessage("8867766969@xmpp-dev.infophone.com", "Hello from Infophone")
        }.onSuccess {
            Log.d("XmppSessionManager", "Connected to $username")
            _session.value = SessionState.Connected(username)
        }.onFailure { e ->
            Log.d("XmppSessionManager", "Failure to $username ${e.message}")
            _session.value = SessionState.Error(e)
        }
    }

    suspend fun goOffline() {
        runCatching {
            xmppClient.setPresence(false, "Offline")
        }
    }

    suspend fun logout() {
        _session.value = SessionState.LoggingOut
        runCatching {
            xmppClient.setPresence(false, "Offline")
            xmppClient.disconnect()
        }
        _session.value = SessionState.Idle
    }
}

sealed class SessionState {
    data object Idle : SessionState()
    data object Connecting : SessionState()
    data class Connected(val username: String) : SessionState()
    data class Error(val error: Throwable) : SessionState()
    data object LoggingOut : SessionState()
}
