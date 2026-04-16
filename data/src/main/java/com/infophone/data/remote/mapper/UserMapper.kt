package com.infophone.data.remote.mapper

import com.infophone.data.remote.dto.UserDto
import com.infophone.domain.model.User

// Extension Function Mapper (Architectural Gatekeeper)
// The Data layer is responsible for knowing the DTO and converting it [4]
fun UserDto.toDomainUser(): User = User(
    id = this.id,
    name = "${this.first_name} ${this.last_name}", // Aggregate data for Domain
    email = this.user_email
)