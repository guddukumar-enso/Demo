package com.infophone.domain.usecase

import com.infophone.domain.model.Post
import com.infophone.domain.repository.PostRepository

class GetPostUseCase(private val postRepository: PostRepository) {
    suspend operator fun invoke(): List<Post> {
        // Example: Add business logic validation here before fetching
        return postRepository.getAllPost()
    }
}