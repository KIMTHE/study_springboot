package com.thekim.hellospring.service

import com.thekim.hellospring.domain.Member
import com.thekim.hellospring.repository.MemoryMemberRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.fail

internal class MemberServiceTest {

    private lateinit var memberService: MemberService
    private lateinit var memberRepository: MemoryMemberRepository

    @BeforeEach
    fun beforeEach() {
        memberRepository = MemoryMemberRepository()
        memberService = MemberService(memberRepository)

    }

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun 회원가입() { //테스트는 한글로 해도됨
        //given
        val member = Member().apply {
            name = "spring"
        }

        //when
        val saveId = memberService.join(member)


        //then
        val findMember = memberService.findOne(saveId)!!
        assertThat(member.name).isEqualTo(findMember.name)
    }

    @Test
    fun 중복_회원_예외() {
        //given
        val member1 = Member().apply {
            name = "spring"
        }

        val member2 = Member().apply {
            name = "spring"
        }

        //when
        memberService.join(member1)
        val e = assertThrows(IllegalStateException::class.java) {
            memberService.join(member2)
        }

        assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")


//        try {
//            memberService.join(member2)
//            fail()
//        } catch (e: IllegalStateException){
//            Assertions.assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
//        }


        //then

    }

    @Test
    fun findMembers() {
    }

    @Test
    fun findOne() {
    }
}