package com.infophone.data.remote.repository

import com.infophone.data.remote.mapper.toDomainPostList
import com.infophone.data.remote.api.PostApi
import com.infophone.domain.model.Post
import com.infophone.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postApi: PostApi
) : PostRepository {
    override suspend fun getAllPost(): List<Post> {
        // 1. Fetch DTO from Ktor Data Source
        // 2. Map DTO to Domain Entity before returning [5]
        val postDtoList = postApi.fetchPosts()
        return postDtoList.toDomainPostList()
    }
}