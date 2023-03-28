package com.example.demo.exam.repository

import com.example.demo.exam.entity.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>