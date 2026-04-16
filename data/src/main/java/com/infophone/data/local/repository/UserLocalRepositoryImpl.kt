package com.infophone.data.local.repository

/*import com.infophone.data.local.dto.UserRealmDto
import com.infophone.data.local.mapper.toDomain
import com.infophone.domain.model.User
import com.infophone.domain.repository.UserRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import javax.inject.Inject

class UserLocalRepositoryImpl @Inject constructor(
    private val realm: Realm
) : UserRepository {

    override suspend fun getUserById(userId: String): User {
        return realm.query<UserRealmDto>("userId == $0", userId).first().find()?.toDomain()!!
    }
}*/

/*
    // For reading from Realm (e.g., caching)
    override fun getAllUsers(): Flow<List<User>> {
        return realm.query<UserRealmDto>()
            .asFlow()
            .map { results -> results.list.map { it.toDomain() } }
    }

    // For writing to Realm (e.g., saving API result)
    override suspend fun saveUser(user: User) {
        realm.write {
            val realmUser = user.toRealm()
            copyToRealm(realmUser, updatePolicy = UpdatePolicy.ALL)
        }
    }*/