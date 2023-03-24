package com.example.demo.blog.entity

import com.example.demo.blog.extension.toSlug
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

/**
 * @Entity
 *      - 테이블과 매핑, JPA가 관리하는 대상
 *      - name : JPA에서 사용할 엔티티 이름을 지정 (default. 클래스명)
 *
 * @Table
 *      - Entity와 매핑할 테이블을 지정 (default. 엔티티 이름)
 *      - Name : 매핑할 테이블 이름
 *      - Catalog : catalog 기능이 있는 DB에서 catalog를 매핑 (default. DB명)
 *      - Schema : schema 기능이 있는 DB에서 schema를 매핑
 *      - uniqueConstraints : DDL 생성시 유니크 제약 조건 생성, 스키마 자동 생성 기능을 사용해서 DDL 생성때 사용
 *
 * @GeneratedValue
 *      - IDENTITY : 기본키 생성을 DB에 위임 (AUTO_INCREMENT)
 *                  Statement.getGeneratedKeys() 를 사용해서 데이터를 저장함과 동시에 생성된 기본 키 값을 얻어 올 수 있음
 *      - SEQUENCE : 데이터베이스 시퀀스를 사용해서 키본키 할당
 *                  데이터베이스 시퀀스에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장
 *      - TABLE : 키 생성 테이블을 사용
 *                  키 생성 전용 테이블 하나를 만들고 여기에 이름과 값으로 사용할 컬럼 생성
 *      - AUTO (default) : 선택한 DB에 따라 자동으로 선택
 *
 * @Column
 *      - 객체 필드를 테이블 컬럼에 매핑
 *      - name : 필드와 매핑할 테이블 컬럼 이름 (default. 객체의 필드 이름)
 *      - nullable : null 값의 허용 여부 설정 (default. true)
 *      - length : 문자 길이 제약조건, String에만 사용(default. 255)
 *      - percision, scale : BigDecimal, BigInteger 타입에 사용. 아주 큰 숫자나 정밀한 소수 다룰때 사용(default. percision = 19, scale = 2)
 *
 * @Enumerated
 *      - enum 타입 매핑할 때 사용(default. EnumType.ORDINAL)
 *      - value : EnumType.ORDINAL -> enum 순서를 데이터베이스에 저장
 *                  EnumType.STRING -> enum 이름을 데이터베이스에 저장
 *
 * @Temporal
 *      - 날짜 타입 매핑할 때 사용(필수 지정)
 *      - value : TemporalType.DATE -> 날짜, 데이터베이스 date 타입과 매핑(2023-03-24)
 *                  TemporalType.TIME -> 시간, 데이터베이스 time 타입과 매핑(21:15:36)
 *                  TemporalType.TIMESTAMP -> 날짜 + 시간, 데이터베이스 timestamp 타입과 매핑(2023-03-24 21:15:36)
 *
 * @Transient
 *      - 매핑하지 않을 필드
 *      - DB에 조회, 저장하지 않고 객체에 임시로 어떤 값을 보관하고 싶을때 사용
 *
 * @Access
 *      - JPA가 엔티티 데이터에 접근하는 방식 지정
 *      - 필드 접근 : AccessType.FIELD(private 도 접근 가능)
 *      - 프로퍼티 접근 : AccessType.PROPERTY(접근자 Getter 사용)
 *      
 * @ManyToOne
 *      - N:1, 단방향, 여러개가 하나에 연결될 때 사용
 *      - @JoinColumn을 통해 조인되는 컬럼을 나타냄
 *      - 양방향을 나타낼때는 1쪽에 @OneToMany(mappedBy = "...") 추가, 1쪽은 읽기만 가능
 *
 * @OneToMany
 *      - 1:N, 단방향, 하나가 여러개에 연결될 때 사용
 *      - @JoinColumn을 통해 조인되는 컬럼을 나타냄
 *
 * @OneToOne
 *      - 1:1
 *
 * @ManyToMany
 *      - N:M
 */

@Entity
class User(
    @Id @GeneratedValue var id: Long? = null,
    var login: String,
    var firstName: String,
    var lastName: String,
    var description: String? = null
)


@Entity
class Article(
    @Id @GeneratedValue var id: Long? = null,
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now()
)