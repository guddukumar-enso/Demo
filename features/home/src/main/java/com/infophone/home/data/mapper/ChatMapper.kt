package com.infophone.home.data.mapper

import com.infophone.home.data.dto.ChatDto
import com.infophone.home.domain.model.Chat

// Extension Function Mapper (Architectural Gatekeeper)
// The Data layer is responsible for knowing the DTO and converting it [4]
fun ChatDto.toDomainChat(): Chat = Chat(
    id = this.id,
    name = "${this.first_name} ${this.last_name}", // Aggregate data for Domain
    email = this.user_email
)

// Extension function to map List<PostDto> to List<Post>
fun List<ChatDto>.toDomainChatList(): List<Chat> = this.map { it.toDomainChat() }