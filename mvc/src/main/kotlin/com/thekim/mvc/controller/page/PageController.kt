package com.thekim.mvc.controller.page

import com.thekim.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PageController {

    // http://localhost:8080/main
    @GetMapping("/main")
    fun main(): String { // rest-controller 와는 달리 페이지를 띄울수있다.
        println("init main")
        return "main.html"
    }

    @ResponseBody // page 를 펼치지않고 반환값을 그대로 응답
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply{
            this.name = "minsu"
        }

        // return "main.html"
    }
}