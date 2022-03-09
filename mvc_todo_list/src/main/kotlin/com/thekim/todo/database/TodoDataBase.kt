package com.thekim.todo.database

// JPA 데이터베이스 대체
data class TodoDataBase(
    var index: Int = 0,
    var todoList: MutableList<Todo> = mutableListOf()
) {
    fun init(){
        this.index = 0
        this.todoList = mutableListOf()
        println("[DEBUG] todo database init")
    }
}