package com.infophone.xmpp

data class XmppConfig(
    val host: String = "xmpp-dev.infophone.com",
    val domain: String = "xmpp-dev.infophone.com",
    val port: Int = 5222,
    val resource: String = "android-app",
    val useTls: Boolean = false
)