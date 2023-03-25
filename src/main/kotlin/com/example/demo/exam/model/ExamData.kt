package com.example.demo.exam.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
data class Message(
    @Id val id: String?,
    val text: String
)