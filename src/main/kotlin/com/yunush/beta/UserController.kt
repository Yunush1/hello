package com.yunush.beta

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class UserController {
    @Autowired
    val userRepository: UserRepository? = null

    @MutationMapping("putUser")
    fun putUser(@Argument id: Long, @Argument name: String, @Argument email: String, @Argument password: String, @Argument dob: String): ResponseEntity<Any> {
        val user = User()
        user.id = id
        user.email = email
        user.name = name
        user.dob = dob
        user.password = password
        return userRepository!!.putUser(user)
    }

    @QueryMapping("getUser")
    fun getUser(@Argument email: String): ResponseEntity<Any> {
        return userRepository!!.getUser(email)
    }

    @QueryMapping("getAll")
    fun getAll(): List<User> {
        return userRepository!!.getAll()
    }
}