package com.thekim.hellospring.service

import com.thekim.hellospring.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.sql.DataSource

//자바 코드로 스프링 빈 등록
@Configuration
class SpringConfig {


//    var dataSource: DataSource
//    var em: EntityManager
    var memberRepository: MemberRepository

//    @Autowired //JDBC 이용
//    constructor(dataSource: DataSource){
//        this.dataSource = dataSource
//    }

//    @Autowired //JPA 이용
//    constructor(em: EntityManager){
//        this.em = em
//    }

    @Autowired //스프링 data JPA 이용
    constructor(memberRepository: MemberRepository){
        this.memberRepository = memberRepository
    }

    @Bean
    fun memberService(): MemberService{
//        return MemberService(memberRepository())
        return MemberService(memberRepository)
    }

//    @Bean
//    fun memberRepository(): MemberRepository{
////        return MemoryMemberRepository()
////        return JdbcMemberRepository(dataSource) //Jdbc 이용
////        return JdbcTemplateMemberRepository(dataSource) //Jdbc template 이용
////        return JpaMemberRepository(em) //jpa 사용
//    }
}