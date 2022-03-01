package com.thekim.mvc.controller.get

import com.thekim.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController    // REST API Controller 동작
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

    @GetMapping(path = ["/hello"]) // GET http://localhost:8080/api/hello
    fun hello(): String {
        return "hello kotlin"
    }

    // 옛날 방식
    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/{name}
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, $age")
        return "$name $age"
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/{name}
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String {
        val name = "kotlin"

        println("${_name}, $age")
        return "$_name $age"
    }

    // http://localhost:8080/api/page?key=value&key=value
    @GetMapping("/get-mapping/query-param") //?name=minsu&age=25
    fun queryParam(
        @RequestParam(name = "name") name: String,
        @RequestParam(value = "age") age: Int
    ): String {
        println("$name, $age")
        return "$name $age"
    }

    // name, age, address, email
    // 자바에서는 변수명에 '-' 을 사용할 수 없다.
    // phoneNumber -> phonenumber, phone-number
    @GetMapping("get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map["phone-number"]
        return map

    }
}