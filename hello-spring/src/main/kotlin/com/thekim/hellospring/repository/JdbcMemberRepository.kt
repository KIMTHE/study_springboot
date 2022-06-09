package com.thekim.hellospring.repository

import com.thekim.hellospring.domain.Member
import com.thekim.hellospring.repository.MemberRepository
import org.springframework.jdbc.datasource.DataSourceUtils
import java.lang.Exception
import java.lang.IllegalStateException
import java.sql.*
import javax.sql.DataSource
import kotlin.Throws

class JdbcMemberRepository : MemberRepository { // 20년 트렌드

    private var dataSource: DataSource
    private val connection: Connection
        get() = DataSourceUtils.getConnection(dataSource)

    constructor(dataSource: DataSource) {
        this.dataSource = dataSource
    }


    override fun save(member: Member): Member {
        val sql = "insert into member(name) values(?)"

        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null

        return try {
            conn = connection
            pstmt = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )

            pstmt.setString(1, member.name)

            pstmt.executeUpdate()
            rs = pstmt.generatedKeys

            if (rs.next()) {
                member.id = rs.getLong(1)
            } else {
                throw SQLException("id 조회 실패")
            }
            member
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findById(id: Long): Member? {
        val sql = "select * from member where id = ?"

        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null

        return try {
            conn = connection
            pstmt = conn.prepareStatement(sql)
            pstmt.setLong(1, id)

            rs = pstmt.executeQuery()

            if (rs.next()) {
                val member = Member().apply {
                    this.id = rs.getLong("id")
                    name = rs.getString("name")
                }
                member
            } else {
                null
            }
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findAll(): List<Member> {
        val sql = "select * from member"

        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null

        return try {
            conn = connection
            pstmt = conn.prepareStatement(sql)
            rs = pstmt.executeQuery()

            val members: MutableList<Member> = ArrayList()
            while (rs.next()) {
                val member = Member().apply {
                    id = rs.getLong("id")
                    name = rs.getString("name")
                }
                members.add(member)
            }
            members
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findByName(name: String): Member? {
        val sql = "select * from member where name = ?"

        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null

        return try {
            conn = connection
            pstmt = conn.prepareStatement(sql)
            pstmt.setString(1, name)

            rs = pstmt.executeQuery()
            if (rs.next()) {
                val member = Member().apply {
                    id = rs.getLong("id")
                    this.name = rs.getString("name")
                }

                return member
            }
            null
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }


    private fun close(conn: Connection?, pstmt: PreparedStatement?, rs: ResultSet?) {
        try {
            rs?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        try {
            pstmt?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        try {
            if (conn != null) {
                close(conn)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    @Throws(SQLException::class)
    private fun close(conn: Connection) {
        DataSourceUtils.releaseConnection(conn, dataSource)
    }


}