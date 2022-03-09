package com.thekim.todo.repository

import com.thekim.todo.database.Todo
import com.thekim.todo.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl : TodoRepository{

    @Autowired
    lateinit var todoDataBase: TodoDataBase

    // JPA 에서는 save 가 create or update 이다.
    override fun save(todo: Todo): Todo? {

        // 입력된 데이터의 index 유무확인

        return todo.index?.let{ index ->
            // update

            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }

        }?: kotlin.run {
            // insert

            todo.apply{
                this.index = ++todoDataBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run{
                todoDataBase.todoList.add(todo)
                this
            }
        }

    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {


        return try {
            todoList.forEach {
                save(it)
            }
            true
        }catch (e: Exception){
            false
        }
    }

    override fun delete(index: Int): Boolean {

        return findOne(index)?.let{
            todoDataBase.todoList.remove(it)
            true
        }?: kotlin.run {
            //삭제할려는 값이 없을 때
            false
        }
    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }
}