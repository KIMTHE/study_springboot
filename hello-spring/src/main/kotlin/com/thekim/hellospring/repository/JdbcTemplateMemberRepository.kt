package com.thekim.hellospring.repository

import com.thekim.hellospring.domain.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import java.sql.ResultSet
import javax.sql.DataSource

class JdbcTemplateMemberRepository : MemberRepository {

    var jdbcTemplate: JdbcTemplate

    //@Autowired 생성자가 하나면 생략가능
    constructor(dataSource: DataSource) {
        this.jdbcTemplate = JdbcTemplate(dataSource)
    }

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters: MutableMap<String, Any?> = HashMap()
        parameters["name"] = member.name

        val key: Number = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()

        return member
    }

    override fun findById(id: Long): Member? {
        val result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id)
        return result.firstOrNull()
    }

    override fun findByName(name: String): Member? {
        val result: List<Member> =
            jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name)
        return result.firstOrNull()
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("select * from member", memberRowMapper())
    }

    private fun memberRowMapper(): RowMapper<Member> {
        return RowMapper<Member> { rs, rowNum ->
            val member = Member().apply {
                id = rs.getLong("id")
                name = rs.getString("name")
            }

            member
        }


    }
}