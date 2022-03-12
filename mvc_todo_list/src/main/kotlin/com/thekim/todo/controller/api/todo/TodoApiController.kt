package com.thekim.todo.controller.api.todo

import com.thekim.todo.model.http.TodoDto
import com.thekim.todo.service.TodoService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/todo")
class TodoApiController(  //todo: test code
    val todoService: TodoService
) {


    //R
    @GetMapping(path = [""])
    fun read(
        @RequestParam(required = false) index: Int?
    ): ResponseEntity<Any?> {

        return index?.let {
            todoService.read(it)
        }?.let {
            return ResponseEntity.ok(it)
        }
            ?: kotlin.run {
                return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/api/todo/all")
                    .build()
            }


    }

    @GetMapping(path = ["/all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    //C
    @PostMapping(path = [""])
    fun create(
        @Valid @RequestBody todoDto: TodoDto
    ): TodoDto? {
        return todoService.create(todoDto)
    }

    //U
    @PutMapping(path = [""]) //create = 201, update = 200
    fun update(
        @Valid @RequestBody todoDto: TodoDto
    ): ResponseEntity<Any>  {
        return todoService.create(todoDto).run{this!!
            if(createdAt == updatedAt){
                ResponseEntity.status(201).body(this)
            }

            else{
                ResponseEntity.status(200).body(this)
            }
        }
    }

    //D
    @DeleteMapping(path = ["/{index}"])
    fun delete(
        @PathVariable(name = "index") _index: Int
    ): ResponseEntity<Any> {

        if (!todoService.delete(_index)) {
            ResponseEntity.status(500)
        }

        return ResponseEntity.ok().build()
    }
}