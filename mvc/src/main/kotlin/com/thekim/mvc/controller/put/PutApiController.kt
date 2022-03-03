package com.thekim.mvc.controller.put

import com.thekim.mvc.model.http.Result
import com.thekim.mvc.model.http.UserRequest
import com.thekim.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping() : String{
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path=["/request-mapping"])
    fun requestMapping() : String{
        return "request-mapping - put method"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(@Valid //해당 bean 에 대해서만 검증
                         @RequestBody userRequest: UserRequest,
                         bindingResult: BindingResult) : ResponseEntity<String> {

        // 에러가 bindingResult 에 담김
        if(bindingResult.hasErrors()){
            // 500 error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage

                msg.append("${field.field} $message\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")

//        // 0. Response
//        return UserResponse().apply{
//
//            // 1. result
//            result = Result().apply{
//                this.resultCode = "OK"
//                this.resultMessage = "성공"
//            }
//
//        }.apply{
//            // 2. description
//            this.description = "~~~~~~~"
//        }.apply {
//            // 3. user mutable list
//            val userList = mutableListOf<UserRequest>()
//
//            userList.add(userRequest)
//
//            userList.add(UserRequest().apply {
//                name = "a"
//                age = 10
//                email = "a@gmail.com"
//                phoneNumber = "010-1111-aaaa"
//            })
//
//            userList.add(UserRequest().apply {
//                name = "b"
//                age = 20
//                email = "a@gmail.com"
//                phoneNumber = "010-1111-bbbb"
//            })
//
//            this.userRequest = userList
//        }
    }
}