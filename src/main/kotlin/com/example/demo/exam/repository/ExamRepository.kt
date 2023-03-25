package com.example.demo.exam.repository

import com.example.demo.exam.model.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>