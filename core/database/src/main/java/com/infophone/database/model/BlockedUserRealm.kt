package com.infophone.database.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class BlockedUserRealm : RealmObject {
    @PrimaryKey
    var blockedId: String = ""

    var blockerId: String = ""

    var createdAt: String = ""

}