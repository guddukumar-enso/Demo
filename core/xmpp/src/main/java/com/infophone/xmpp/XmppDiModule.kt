package com.infophone.xmpp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.ReconnectionManager
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object XmppDiModule {

    @Provides
    @Singleton
    fun provideXmppConfig(): XmppConfig = XmppConfig(
        host = "xmpp-dev.infophone.com",
        domain = "xmpp-dev.infophone.com",
        port = 5222,
        resource = "android-app",
        useTls = false
    )

    @Provides
    @Singleton
    fun provideXmppConnectionConfiguration(config: XmppConfig): XMPPTCPConnectionConfiguration {
        return XMPPTCPConnectionConfiguration.builder()
            .setXmppDomain(config.domain)
            .setUsernameAndPassword("9731581515","Anu26@enso")
            .setHost(config.host)
            .setPort(config.port)
            .setSecurityMode(
                if (config.useTls)
                    ConnectionConfiguration.SecurityMode.required
                else
                    ConnectionConfiguration.SecurityMode.ifpossible
            )
            .setCompressionEnabled(false)
            .setSendPresence(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideXmppConnection(
        configuration: XMPPTCPConnectionConfiguration
    ): XMPPTCPConnection {
        val connection = XMPPTCPConnection(configuration)
        ReconnectionManager.getInstanceFor(connection).enableAutomaticReconnection()
        return connection
    }

    @Provides
    @Singleton
    fun provideXmppClient(
        connection: XMPPTCPConnection
    ): XmppClient = XmppClient(connection)
}
