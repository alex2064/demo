package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @SpringBootApplication
 * 		- 스프링부트의 자동설정, bean 읽기와 생성이 자동 설정
 * 		- 설정을 읽어가기 때문에 이 어노테이션을 포함한 클래스는 항상 프로젝트 최상단에 위치
 * 		- @SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan 를 포함
 *
 * @SpringBootConfiguration
 * 		- spring에 빈팩토리를 위한 오브젝트 설정을 담당하는 클래스라고 인식하게하는 어노테이션
 * 		- 하는 일은 @Configuration 과 거의 같지만 구성을 자동으로 찾을 수 있음
 *
 * @EnableAutoConfiguration
 * 		- 미리 정의되어 있는 bean들을 가져와서 등록
 * 		- spring-boot-autoconfigure > META-INF > spring.factories에 정의된 bean 위치
 *
 * @ComponentScan
 * 		- 현재 패키지 이하에서 아래와 같은 어노테이션이 붙어있는 클래스를 찾아서 bean으로 등록
 * 		- @Component, @Configuration, @Repository, @Service, @Controller, @RestController
 *
 */
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
