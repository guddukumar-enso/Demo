package com.infophone.domain.repository

import com.infophone.domain.model.Post

interface PostRepository {
    suspend fun getAllPost(): List<Post>
}