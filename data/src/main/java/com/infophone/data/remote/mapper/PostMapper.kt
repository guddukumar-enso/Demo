package com.infophone.data.remote.mapper

import com.infophone.data.remote.dto.PostDto
import com.infophone.domain.model.Post

fun PostDto.toDomainPost(): Post = Post(
    id = this.id,
    body = this.body,
    title = this.title,
    userId = this.userId
)

// Extension function to map List<PostDto> to List<Post>
fun List<PostDto>.toDomainPostList(): List<Post> = this.map { it.toDomainPost() }