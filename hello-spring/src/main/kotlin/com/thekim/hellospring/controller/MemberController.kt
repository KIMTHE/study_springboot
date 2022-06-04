package com.thekim.hellospring.controller

import com.thekim.hellospring.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

//스프링 컨테이너가 실행될 때, 컨트롤러 객체를 만들어서 가지고있는다.
@Controller
class MemberController {

    private final val memberService: MemberService

    @Autowired //(dependency injection)컨테이너가 서비스랑 컨트롤러를 연결함
    constructor(memberService: MemberService){
        this.memberService = memberService
    }
}