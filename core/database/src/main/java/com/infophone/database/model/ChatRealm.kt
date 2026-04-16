package com.infophone.database.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ChatRealm : RealmObject {
    @PrimaryKey
    var chatId: String=""
    var type: String =""
    var groupId: String=""
    var chatUserId: String=""
    var isMuted: Boolean=false
    var isArchived: Boolean=false
    var createdBy: String=""
    var createdAt: String=""
    var updatedAt: String=""
}