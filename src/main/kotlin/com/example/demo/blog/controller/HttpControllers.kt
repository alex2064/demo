package com.example.demo.blog.controller

import com.example.demo.blog.entity.Article
import com.example.demo.blog.entity.User
import com.example.demo.blog.repository.ArticleRepository
import com.example.demo.blog.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController (
    private val repository: ArticleRepository,
    private val userRepository: UserRepository
) {

    @GetMapping("/")
    fun findAll(): Iterable<Article> = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    fun findOne(@PathVariable slug: String): Article {
        return repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
    }
}


@RestController
@RequestMapping("/api/user")
class UserController(
    private val repository: UserRepository
) {

    @GetMapping("/")
    fun findAll(): MutableIterable<User> = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String): User =
        repository.findByLogin(login) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
}
