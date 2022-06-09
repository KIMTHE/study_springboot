package com.thekim.hellospring.service

import com.thekim.hellospring.domain.Member
import com.thekim.hellospring.repository.MemberRepository
import com.thekim.hellospring.repository.MemoryMemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

//@Service
@Transactional
class MemberService {

    private final val memberRepository: MemberRepository

    //@Autowired //(dependency injection)
    constructor(memberRepository: MemberRepository) {
        this.memberRepository = memberRepository
    }


    /*
    회원가입
     */
    fun join(member: Member): Long{
        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member) //중복회원검증

        memberRepository.save(member)
        return member.id!!
    }

    private fun validateDuplicateMember(member: Member) {
        memberRepository.findByName(member.name!!)?.let {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    /*
    전체 회원 조회
     */
    fun findMembers(): List<Member>{
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Member?{
        return memberRepository.findById(memberId)
    }
}