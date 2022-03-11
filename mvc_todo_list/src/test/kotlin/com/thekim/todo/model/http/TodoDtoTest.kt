package com.thekim.todo.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import javax.validation.Validation
import javax.validation.Validator

class TodoDtoTest {

    // validator 가져옴
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest(){
        val todoDto = TodoDto().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2022-03-12 01:47:00"

        }

        val result = validator.validate(todoDto)

//        result.forEach {
//            println(it.propertyPath.last().name)
//            println(it.message)
//            println(it.invalidValue)
//        }

        Assertions.assertEquals(true,result.isEmpty())
    }

}