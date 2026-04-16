package com.infophone.database.domain


import com.infophone.database.domain.model.FailedMessage
import com.infophone.database.domain.model.FailedMessageRealm
import io.realm.kotlin.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.realm.kotlin.ext.query

class FailedMessageRepositoryImpl(
    private val realm: Realm
) : FailedMessageRepository {

    override suspend fun insertFailedMessage(message: FailedMessage) {
        realm.write {
            copyToRealm(
                FailedMessageRealm().apply {
                    id = message.id
                    contactId = message.contactId
                    text = message.text
                    timestamp = message.timestamp
                    reason = message.reason
                }
            )
        }
    }

    override suspend fun getFailedMessages(): List<FailedMessage> =
        withContext(Dispatchers.IO) {
            val results = realm.query<FailedMessageRealm>().find()

            results.map { obj ->
                FailedMessage(
                    id = obj.id,
                    contactId = obj.contactId,
                    text = obj.text,
                    timestamp = obj.timestamp,
                    reason = obj.reason
                )
            }
        }

    override suspend fun deleteFailedMessage(id: String) {
        withContext(Dispatchers.IO) {
            realm.write {
                val toDelete = this.query<FailedMessageRealm>("id == $0", id).find()
                delete(toDelete)
            }
        }
    }
}