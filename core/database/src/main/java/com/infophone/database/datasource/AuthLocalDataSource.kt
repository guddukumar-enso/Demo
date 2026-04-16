package com.infophone.database.datasource


import com.infophone.database.domain.model.UserRealm
import com.infophone.database.entity.UserEntity
import com.infophone.database.mapper.toEntity
import com.infophone.database.mapper.toRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val realm: Realm
) {
    fun observeUser(): Flow<UserEntity?> =
        realm.query<UserRealm>()
            .first()
            .asFlow()
            .map { it.obj?.toEntity() }

    suspend fun saveUser(user: UserEntity) {
        realm.write {
            copyToRealm(user.toRealm(), UpdatePolicy.ALL)
        }
    }

    suspend fun clearUser() {
        realm.write {
            delete<UserRealm>()
        }
    }
}


