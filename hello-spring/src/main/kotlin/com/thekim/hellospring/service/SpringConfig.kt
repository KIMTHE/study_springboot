package com.thekim.hellospring.service

import com.thekim.hellospring.repository.MemberRepository
import com.thekim.hellospring.repository.MemoryMemberRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//자바 코드로 스프링 빈 등록
@Configuration
class SpringConfig {

    @Bean
    fun memberService(): MemberService{
        return MemberService(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository{
        return MemoryMemberRepository()
    }
}