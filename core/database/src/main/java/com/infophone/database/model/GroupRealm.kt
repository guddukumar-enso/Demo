package com.infophone.database.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class GroupRealm: RealmObject {
    @PrimaryKey
    var groupId: String=""
    var name: String=""
    var description: String=""
    var imageUrl: String=""
    var createdBy: String=""
    var createdAt: String=""
    var updatedAt: String=""
    var deletedAt: String=""
    var maxAllowedMembers: Int=0
    var isArchived: Boolean=false
    var isDeleted: Boolean=false
    var isMuted: Boolean=false
}