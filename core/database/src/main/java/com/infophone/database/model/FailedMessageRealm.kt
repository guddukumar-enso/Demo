package com.infophone.database.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class FailedMessageRealm : RealmObject {
    @PrimaryKey
    var id: String = ""
    var contactId: Long = 0L
    var text: String = ""
    var timestamp: Long = 0L
    var reason: String? = null
}
