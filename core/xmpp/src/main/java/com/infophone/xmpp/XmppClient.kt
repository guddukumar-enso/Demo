package com.infophone.xmpp

import android.provider.ContactsContract
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import org.jivesoftware.smack.ConnectionListener
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.packet.Presence
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jxmpp.jid.impl.JidCreate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XmppClient @Inject constructor(
    private val connection: XMPPTCPConnection
) {

    private val chatManager by lazy { ChatManager.getInstanceFor(connection) }

    private val _state = MutableStateFlow(ConnectionState())
    val state: StateFlow<ConnectionState> = _state.asStateFlow()

    private val _incomingMessages = MutableSharedFlow<IncomingMessage>(
        replay = 0,
        extraBufferCapacity = 64
    )
    val incomingMessages: SharedFlow<IncomingMessage> = _incomingMessages.asSharedFlow()

    init {
        connection.addConnectionListener(object : ConnectionListener {
            override fun connected(connection: XMPPConnection) {
                _state.update { it.copy(isConnected = true, lastError = null) }
            }

            override fun authenticated(connection: XMPPConnection, resumed: Boolean) {
                _state.update { it.copy(isAuthenticated = true, lastError = null) }
            }

            override fun connectionClosed() {
                _state.update { it.copy(isConnected = false, isAuthenticated = false) }
            }

            override fun connectionClosedOnError(e: Exception) {
                _state.update { it.copy(isConnected = false, isAuthenticated = false, lastError = e) }
            }
        })

        // 1:1 incoming messages
        chatManager.addIncomingListener { from, message, _ ->
            Log.d("XMPP_INCOMING", message.body)
            val body = message.body ?: return@addIncomingListener
            _incomingMessages.tryEmit(
                IncomingMessage(
                    from = from.asBareJid().toString(),
                    body = body,
                    stanzaId = message.stanzaId
                )

            )
        }
    }

    suspend fun connectAndLogin(usernameLocalPart: String, password: String) {
        withContext(Dispatchers.IO) {
            if (!connection.isConnected) {
                Log.d("XMPP", "Connecting...")
                connection.connect()
            }
            if (!connection.isAuthenticated) {
                Log.d("XMPP", "Logging in...")
                connection.login(usernameLocalPart, password)

                Log.d("XMPP_SUCCESS", "Logged in successfully")
            }
        }
    }

    suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            if (connection.isConnected) connection.disconnect()
        }
    }

    suspend fun sendMessage(toBareJid: String, body: String) {
        withContext(Dispatchers.IO) {
            check(connection.isAuthenticated) { "XMPP not authenticated" }

            val jid = JidCreate.entityBareFrom(toBareJid) // "user2@example.com"
            val chat = chatManager.chatWith(jid)
            chat.send(body)
        }
    }

    suspend fun setPresence(online: Boolean, status: String? = null) {
        withContext(Dispatchers.IO) {
            if (!connection.isConnected) return@withContext

            // Use the PresenceBuilder to create the Presence stanza
            val presenceBuilder = connection.stanzaFactory.buildPresenceStanza()
            presenceBuilder.ofType(if (online) Presence.Type.available else Presence.Type.unavailable)
            status?.let { presenceBuilder.setStatus(it) }

            val presence = presenceBuilder.build()
            connection.sendStanza(presence)
        }
    }
}

data class ConnectionState(
    val isConnected: Boolean = false,
    val isAuthenticated: Boolean = false,
    val lastError: Exception? = null
)

data class IncomingMessage(
    val from: String,
    val body: String,
    val stanzaId: String? = null
)
