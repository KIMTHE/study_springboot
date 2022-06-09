package com.thekim.hellospring.service

import com.thekim.hellospring.domain.Member
import com.thekim.hellospring.repository.MemberRepository
import com.thekim.hellospring.repository.MemoryMemberRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional //테스트 케이스가 끝나면 rollback 되어 db에 반영되지 않음
internal class MemberServiceIntegrationTest {


    @Autowired
    lateinit var memberService: MemberService
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
//    @Commit //트랜잭션 이후 실제 DB에 반영
    fun 회원가입() { //테스트는 한글로 해도됨
        //given
        val member = Member().apply {
            name = "spring spring"
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
    }
}