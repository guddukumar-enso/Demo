package com.infophone.data.local.mapper

/*import com.infophone.data.local.dto.UserRealmDto
import com.infophone.domain.model.User*/

/**
 * Data Layer → Domain Layer (Read/Retrieval Flow)
 * Every time you read data from the Realm database (e.g., in ItemRepositoryImpl.getAllItems()),
 * you must use this mapper to ensure the data passed up to the Use Cases and Presentation layers is a pure Item entity, not tied to Realm.
 */
/*
fun UserRealmDto.toDomain(): User {
    return User(id, name, email)
}
*/

/**
 * Domain Layer → Data Layer (Write/Storage Flow)
 * Every time you save or update an item (e.g., in ItemRepositoryImpl.saveItem(item)),
 * you must use this mapper to translate the Domain entity into a Realm object that the database can actually store.
 */
/*
fun User.toRealm(): UserRealmDto {
    return UserRealmDto().apply {
        id = this@toRealm.id
        name = this@toRealm.name
        email = this@toRealm.email
    }
}*/
