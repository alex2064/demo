import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * plugins
 * 		- 미리 구성해둔 task들의 그룹
 * 		- 빌드과정에서 필요한 정보들을 포함하고 있으며 필요에 따라 커스터 마이징 가능
 *		- kotlin(<...>) 은 kotlin 전용 id를 축약해 놓은 것
 * 		- id("org.jetbrains.kotlin.<...>") 와 같은 의미
 *
 * id("org.springframework.boot")
 * 		- Spring Boot를 사용하기 위한 플러그인
 *
 * id("io.spring.dependency-management")
 * 		- 의존성의 버전 관리를 일괄적으로 하기 위한 플러그인
 *
 * kotlin("jvm")
 * 		- 프로젝트에서는 jvm을 타겟으로 한다고 명시
 *
 * kotlin("plugin.spring")
 * 		- allopen 플러그인(plugin.allopen 포함)
 * 		- 코틀린의 클래스는 기본적으로 final로써 open 키워드를 명시적으로 사용하지 않으면 상속이 불가능
 * 		- Spring의 AOP는 cglib을 사용하는데 이는 상속을 통해서 Proxy 패턴을 사용
 * 		- 해당 플러그인은 아래의 클래스를 open 기본으로 해줌
 * 			- @Component
 * 			- @Component를 상속받는 @Configuration, @Controller, @RestController, @Service, @Repository
 * 			- @Async
 * 			- @Transactional
 * 			- @Cacheable
 * 			- SpringBootTest
 *
 * kotlin("plugin.jpa")
 * 		- JPA를 사용하기 위한 플러그인(plugin.noarg 포함)
 *
 * kotlin("kapt")
 * 		- Kotlin Annotation Processing Tool
 * 		- 코틀린에서 Annotation processing을 지원하기위해서 사용
 *		- 기존 java annotation과 호환되지 않을 수 있음(ex. Lombok) => 코틀린에서는 Lombok을 사용할 필요가 없으므로 제거
 */
plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
	kotlin("kapt") version "1.7.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

/**
 * dependencies
 * 		- 의존성, jar파일의 집합
 *
 * implementation("org.springframework.boot:spring-boot-starter-web")
 * 		- spring boot를 이용하기 위한 의존성
 *
 * implementation("org.springframework.boot:spring-boot-starter-data-jpa")
 * 		- JPA를 사용하기 위한 의존성
 *
 * testImplementation("org.springframework.boot:spring-boot-starter-test")
 * 		- Spring boot 테스트를 이용하기 위한 의존성
 *
 * implementation("org.jetbrains.kotlin:kotlin-reflect")
 * 		- kotlin은 런타임 라이브러리 용량을 줄이기위해 기본적으로 reflect를 제공하지 않음
 * 		- reflect를 이용하기 위해 추가
 *
 * implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
 * 		- kotlin의 필수적인 기능들을 제공
 * 		- **내가 추가한 것이여서 확인 필요
 *
 * kapt("org.springframework.boot:spring-boot-configuration-processor")
 * 		- application.yml 파일의 key값이 driver-class-name과 같이 하이픈(-)이 포함된 경우 카멜표기법으로 변환된 key가 멤버변수와 연결
 *
 *
 */
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

/**
 * allOpen
 * 		- plugin.spring에서 Open 해주는 것 외에 추가로 Open 해줄 것 명시
 *
 * annotation("jakarta.persistence.Entity")
 * annotation("jakarta.persistence.MappedSuperclass")
 * annotation("jakarta.persistence.Embeddable")
 * 		- JPA에서 사용하게 추가적으로 open
 * 		- open 키워드가 없으면 Proxy 기반으로 Lazy 로딩을 할 수 없음
 *
 *
 */
allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
