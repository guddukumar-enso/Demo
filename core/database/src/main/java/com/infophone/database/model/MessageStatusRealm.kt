package com.infophone.database.domain.model

import io.realm.kotlin.types.RealmObject

class MessageStatusRealm : RealmObject {
    var messageId: String = ""
    var status: Int = 0
    var userId: String = ""
    var updatedAt: String = ""
}