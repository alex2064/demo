package com.example.demo.exam.service

import com.example.demo.exam.extension.toList
import com.example.demo.exam.model.Message
import com.example.demo.exam.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(
    val messageRepository: MessageRepository
) {
    fun findMessages(): List<Message> = messageRepository.findAll().toList()

    fun findMessageById(id: String) = messageRepository.findById(id).toList()

    fun save(message: Message) {
        messageRepository.save(message)
    }
}