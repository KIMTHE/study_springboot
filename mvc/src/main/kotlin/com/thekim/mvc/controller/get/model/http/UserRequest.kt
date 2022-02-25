package com.thekim.mvc.controller.get.model.http

data class UserRequest(
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var address: String? = null
) {
}