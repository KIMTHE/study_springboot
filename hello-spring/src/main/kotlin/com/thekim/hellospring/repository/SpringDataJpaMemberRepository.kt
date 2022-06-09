package com.thekim.hellospring.repository

import com.thekim.hellospring.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

//JpaRepository 을 상속받으면, 스프링 data jpa 가 알아서 인터페이스 구현체를 만들어서 빈으로 등록함
interface SpringDataJpaMemberRepository: JpaRepository<Member, Long>, MemberRepository {


    //메소드 이름의 패턴을 통해서, 알아서 구현을 만들어줌
    override fun findByName(name: String): Member?

}