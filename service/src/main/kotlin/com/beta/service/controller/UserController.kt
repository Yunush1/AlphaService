package com.beta.service.controller

import com.beta.service.data.User
import com.beta.service.data.db.DbUser
import com.beta.service.data.message.SuccessMessage
import com.beta.service.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController {
    @Autowired
    val userService:UserService?=null

//    @MutationMapping("putUserMetadata")
    @PostMapping("/put")
    fun putUser(@RequestBody dbUser: DbUser):SuccessMessage{
    println(dbUser.email)
        return userService!!.putUser(dbUser)
    }
//    @QueryMapping("getUser")
    @GetMapping("/get/{userId}")
    fun getUser(@PathVariable userId:String): User? {
        return userService!!.getUser(userId)
    }
}