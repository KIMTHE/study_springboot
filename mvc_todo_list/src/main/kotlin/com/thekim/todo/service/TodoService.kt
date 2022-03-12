package com.thekim.todo.service

import com.thekim.todo.database.Todo
import com.thekim.todo.database.convertTodo
import com.thekim.todo.model.http.TodoDto
import com.thekim.todo.model.http.convertTodoDto
import com.thekim.todo.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service


@Service
class TodoService(  //todo: test code
    val todoRepositoryImpl: TodoRepositoryImpl
) {

    //C
    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    //R
    fun read(index: Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map {
            TodoDto().convertTodoDto(it)
        }.toMutableList()
    }

    //U
    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    //D
    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}