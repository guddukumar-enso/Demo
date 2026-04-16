package com.infophone.database.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class MessageRealm: RealmObject {
    @PrimaryKey
    var messageId: String = ""
    var chatId: String = ""
    var senderId: String = ""
    var receiverId: String = ""
    var content: String = ""
    var messageType: Int = 0
    var metadata: String=""
    var createdAt: String = ""
    var updatedAt: String = ""
    var deletedAt: String = ""
    var isDeleted: Boolean = false
    var isEdited: Boolean = false
    var replyToMessageId: String = ""
}