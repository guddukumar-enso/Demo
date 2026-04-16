package com.infophone.database.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ChatParticipantsRealm : RealmObject {
    @PrimaryKey
    var id: String=""
    var chatId: String=""
    var userId: String=""
    var createdAt: String=""
    var role: String=""
    var joinedAt: String=""
    var leftAt: String=""
    var settings: String=""
}