package com.thekim.hellospring.repository

import com.thekim.hellospring.domain.Member
import java.util.*

interface MemberRepository {
    fun save(member: Member): Member

    fun findById(id: Long): Member?

    fun findByName(name: String): Member?

    fun findAll(): List<Member>
}