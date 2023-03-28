package com.example.demo.exam.controller

import com.example.demo.exam.entity.Message
import com.example.demo.exam.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/message")
class MessageController(
    val messageService: MessageService
) {

    @GetMapping("/")
    fun index(@RequestParam name: String): String = "Hello, ${name}"

    @GetMapping("/list")
    fun indexList(): List<Message> {
        return listOf(Message("1", "Hello!"),
            Message("2", "Bonjour!"),
            Message("3", "Privet!"))
    }

    @PostMapping("/")
    fun post(@RequestBody message: Message) {
        messageService.save(message)
    }

}

