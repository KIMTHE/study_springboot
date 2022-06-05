package com.thekim.hellospring.controller

import com.thekim.hellospring.domain.Member
import com.thekim.hellospring.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

//스프링 컨테이너가 실행될 때, 컨트롤러 객체를 만들어서 가지고있는다.
@Controller
class MemberController {

    private final val memberService: MemberService

    @Autowired //(dependency injection)컨테이너가 서비스랑 컨트롤러를 연결함
    constructor(memberService: MemberService){
        this.memberService = memberService
    }

    @GetMapping("members/new")
    fun createForm(): String{
        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(form: MemberForm): String{
        val member = Member().apply {
            name = form.name
        }

        memberService.join(member)

        return "redirect:/"
    }

    @GetMapping("/members")
    fun list(model: Model): String{
        val members = memberService.findMembers()
        model.addAttribute("members",members)
        return "members/memberList"
    }
}