package com.thekim.hellospring.repository

import com.thekim.hellospring.domain.Member
import org.springframework.stereotype.Repository
import java.util.*

//@Repository
class MemoryMemberRepository : MemberRepository {

    companion object{
        var store: MutableMap<Long,Member> = hashMapOf()
        var sequence: Long = 0L

    }

    override fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    override fun findById(id: Long): Member? {
        return store[id]
    }

    override fun findByName(name: String): Member? {
        return store.values
            .find { member -> member.name == name }

    }

    override fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore(){
        store.clear()
    }
}