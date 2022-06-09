package com.thekim.hellospring.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 직접 생성해주는 전략
    var id: Long? = null

    var name: String? = null
}