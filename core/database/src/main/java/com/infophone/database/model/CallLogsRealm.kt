package com.infophone.database.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CallLogsRealm : RealmObject {
    @PrimaryKey
    var id: String = ""
    var roomId: String = ""
    var type: Int = 0
    var duration: Int=0
    var chatId: String=""
    var participants: RealmList<String> = realmListOf()
}