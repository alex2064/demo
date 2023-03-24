package com.example.demo.blog.property

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @ConfigurationProperties(<...>)
 *      - application.yml에 있는 key(<...>)를 class에 연결
 *      - kapt("org.springframework.boot:spring-boot-configuration-processor") 의존성 필요
 *      - @Autowired를 사용해서 클래스를 생성해서 사용
 */
@ConfigurationProperties("blog")
data class BlogProperties(
    var title: String,
    val banner: Banner
) {
    data class Banner(
        val title: String? = null,
        val content: String
    )
}